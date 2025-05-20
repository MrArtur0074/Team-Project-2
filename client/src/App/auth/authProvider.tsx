import React, { useContext, useState } from "react";
import { useNavigate } from "react-router-dom";
import AuthContext from "./authContext";
import axios from "axios";

/**
 * Интерфейс для данных входа в систему
 * @property {string} username - Имя пользователя
 * @property {string} password - Пароль пользователя
 */
export interface LoginData {
  username: string;
  password: string;
}

export interface RegistrationData {
  username: string;
  email: string;
  name: string;
  surname: string;
  password: string;
  dateOfBirth?: string;
  bloodType?: string;
  gender?: string;
}

/**
 * Интерфейс данных пользователя получаемых с бд
 * @property {string} email - Электронная почта пользователя
 * @property {number} id - Уникальный идентификатор пользователя
 * @property {string} password - Пароль пользователя
 * @property {string} role - Роль пользователя в системе
 * @property {string} username - Имя пользователя
 */
export interface UserData {
  email: string;
  id: number;
  password: string;
  role: string;
  username: string;
}

/**
 * Компонент для авторизации пользователя
 * @param {React.ReactNode} children - Дочерние компоненты
 * @return {JSX.Element} - Компонент для авторизации пользователя
 */
const AuthProvider = ({
  children,
}: {
  children: React.ReactNode;
}): JSX.Element => {
  const navigate = useNavigate();

  /**
   * Состояние для хранения данных пользователя * */
  const [user, setUser] = useState<UserData | null>(null);
  /**
   *  Состояние для хранения токена доступа*/
  const [accessToken, setAccessToken] = useState<string>(
    localStorage.getItem("donationAccessToken") || ""
  );
  /**
   * Состояние для хранения токена обновления*/
  const [refreshToken, setRefreshToken] = useState<string>(
    localStorage.getItem("donationRefreshToken") || ""
  );
  /**
   * Состояние для хранения аутентификации пользователя*/
  const [isAuthenticated, setIsAuthenticated] = useState<boolean>(() => {
    const storedValue = localStorage.getItem("isAuthenticatedOndonation");
    return storedValue === "true";
  });
  /**
   * Состояние для хранения админ ли пользователя*/
  const [isAdmin, setIsAdmin] = useState<boolean>(false);
  /**
   * Функция для регистрации пользователя
   * @param {RegistrationData} data - Данные для регистрации
   * @return {Promise<boolean>} - Возвращает true если пользователь успешно зарегистрирован, иначе false
   */
  async function register(data: RegistrationData): Promise<boolean> {
    try {
      console.log(data);
      const response = await axios.post(
        `http://127.0.0.1:8080/auth/register`,
        data,
        {
          headers: {
            "Content-Type": "application/json",
          },
        }
      );
      const res = response.data;
      if (!res) return false;
      console.log(`response from server: + ${res}`);

      if (response.status === 200) {
        const userRequest = await axios.get(
          `http://127.0.0.1:8080/user-api/profile`,
          {
            headers: {
              Authorization: `Bearer ${res.accessToken}`,
            },
          }
        );
        const userData = userRequest.data;
        if (!userData) return false;

        setUser(userData);
        setIsAuthenticated(true);
        setAccessToken(res.accessToken);
        setRefreshToken(res.refreshToken);
        localStorage.setItem("isAuthenticatedOndonation", "true");
        localStorage.setItem("donationAccessToken", res.accessToken);
        localStorage.setItem("donationRefreshToken", res.refreshToken);
        return true;
      }
      return false;
    } catch (e) {
      if (axios.isAxiosError(e)) {
        console.error("Axios error:", e.response?.data || e.message);
        console.error("Status code:", e.response?.status);
      } else {
        console.error("Unexpected error:", e);
      }
      console.error("Registration failed. Try again later.");
      return false;
    }
  }

  /**
   * Функция для выполнения входа пользователя
   * @param {LoginData} data - Данные для входа
   * @return {Promise<boolean>} - Возвращает true если пользователь успешно вошел, иначе false
   */
  async function login(data: LoginData): Promise<boolean> {
    try {
      const response = await axios.post(
        `http://127.0.0.1:8080/auth/login`,
        data,
        {
          headers: {
            "Content-Type": "application/json",
          },
        }
      );
      const res = response.data;
      console.log("login request" + res.accessToken);
      if (!res) return false;

      const userRequest = await axios.get(
        `http://localhost:8080/user-api/profile`,
        {
          headers: {
            Authorization: `Bearer ${res.accessToken}`,
          },
        }
      );
      const userData = userRequest.data;
      if (!userData) return false;

      setUser(userData);
      setIsAuthenticated(true);
      setAccessToken(res.accessToken);
      setRefreshToken(res.refreshToken);
      localStorage.setItem("isAuthenticatedOndonation", "true");
      localStorage.setItem("donationAccessToken", res.accessToken);
      localStorage.setItem("donationRefreshToken", res.refreshToken);
      return true;
    } catch {
      console.error("Unauthorized: Invalid username or password");
      return false;
    }
  }

  /**
   * Функция для выполнения выхода пользователя
   */
  const logOut = (): void => {
    setUser(null);
    setIsAuthenticated(false);
    setAccessToken("");
    setRefreshToken("");
    localStorage.removeItem("donationAccessToken");
    localStorage.removeItem("donationRefreshToken");
    localStorage.removeItem("isAuthenticatedOndonation");
    navigate("/auth/login");
  };

  async function refreshAccessToken(token: string) {
    try {
      const response = await axios.get(
        `http://localhost:8080/jwt-api/refresh-accessToken`,
        {
          headers: {
            Authorization: `Bearer ${token}`,
          },
        }
      );
      const res = response.data;

      if (!res) return "";
      setAccessToken(res.accessToken);
      localStorage.setItem("donationAccessToken", res.accessToken);
      return res.accessToken;
    } catch {
      console.error("Unauthorized: Invalid username or password");
      return "";
    }
  }

  return (
    <AuthContext.Provider
      value={{
        isAuthenticated,
        isAdmin,
        accessToken,
        setAccessToken,
        refreshToken,
        refreshAccessToken,
        setRefreshToken,
        login,
        logOut,
        register,
        user,
      }}>
      {children}
    </AuthContext.Provider>
  );
};

export default AuthProvider;

// eslint-disable-next-line react-refresh/only-export-components
export const useAuth = () => {
  return useContext(AuthContext);
};
