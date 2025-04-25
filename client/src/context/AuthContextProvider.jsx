import React, { useContext, useState, useEffect } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";

export const authContext = React.createContext();
export const useAuth = () => useContext(authContext);

const API = "http://localhost:8081/";

const AuthContextProvider = ({ children }) => {
  const [user, setUser] = useState(null); // Сохраняем пользователя
  const navigate = useNavigate();

  useEffect(() => {
    // Попробуем загрузить пользователя из localStorage при загрузке приложения
    const token = localStorage.getItem("authToken");
    if (token) {
      // Если токен существует, можно попробовать загрузить информацию о пользователе
      axios
        .get(`${API}auth/me`, {
          headers: {
            Authorization: `Bearer ${token}`,
          },
        })
        .then((res) => {
          setUser(res.data);
        })
        .catch((err) => {
          console.error("Ошибка при получении пользователя:", err);
          localStorage.removeItem("authToken"); // Очистить токен, если запрос не удался
        });
    }
  }, []);

  async function register(formData) {
    console.log(formData);
    try {
      const headers = {
        "Content-Type": "application/json",
      };

      let res = await axios.post(
        `${API}auth/register`,
        formData, // no need to stringify formData
        { headers }
      );

      console.log("Ответ сервера:", res.data);

      const token = res.data.token;
      localStorage.setItem("authToken", token);

      // Загрузим пользователя после успешной регистрации
      const userData = await axios.get(`${API}auth/me`, {
        headers: {
          Authorization: `Bearer ${token}`,
        },
      });

      setUser(userData.data); // Обновляем состояние с пользователем
      navigate("/"); // Перенаправляем на главную страницу после регистрации
    } catch (err) {
      console.error("Registration error:", err);
      console.error("Ответ сервера:", err.response?.data);
    }
  }

  async function login(formData) {
    console.log(formData);
    
    try {
      const headers = {
        "Content-Type": "application/json",
      };

      let res = await axios.post(
        `${API}auth/login`,
        formData, // no need to stringify formData
        { headers }
      );

      console.log("Ответ сервера:", res.data);

      const token = res.data.token;
      localStorage.setItem("authToken", token);

      // Загрузим пользователя после успешного логина
      const userData = await axios.get(`${API}auth/me`, {
        headers: {
          Authorization: `Bearer ${token}`,
        },
      });

      setUser(userData.data); // Обновляем состояние с пользователем
      navigate("/"); // Перенаправляем на главную страницу после логина
    } catch (err) {
      console.error("Login error:", err);
      console.error("Ответ сервера:", err.response?.data);
    }
  }

  return (
    <authContext.Provider
      value={{
        user,
        register,
        login,
      }}
    >
      {children}
    </authContext.Provider>
  );
};

export default AuthContextProvider;
