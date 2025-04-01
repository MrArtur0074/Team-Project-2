import React, { useContext, useState } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";

export const authContext = React.createContext();
export const useAuth = () => useContext(authContext);

const API = "https://pi.sanarip.org/donation/";

const AuthContextProvider = ({ children }) => {
  const [user, setUser] = useState(null); // Сохраняем пользователя
  const navigate = useNavigate();

  async function register(formData) {
    try {
      const headers = {
        "Content-Type": "application/json", // Указываем, что отправляем JSON
      };
  
      let res = await axios.post(
        `${API}auth/register`,
        JSON.stringify(formData), // Отправляем как JSON
        { headers }
      );
  
      console.log("Ответ сервера:", res.data);
  
      // Сохраняем JWT токен в localStorage или sessionStorage
      const token = res.data.token; // Пример, если сервер возвращает токен
      localStorage.setItem("authToken", token);
  
      // Перенаправляем пользователя после регистрации
      navigate("/auth");
    } catch (err) {
      console.error("Registration error:", err);
      console.error("Ответ сервера:", err.response?.data); // Показываем ответ от сервера
    }
  }
  async function login(formData, username) {
    try {
      let res = await axios.post(`${API}auth/login`, formData, {
        headers: { "Content-Type": "application/json" },
      });
      localStorage.setItem("tokens", JSON.stringify(res.data));
      localStorage.setItem("username", username); // JSON.stringify не нужен
      setUser(username); // Обновляем пользователя в `useState`
      navigate("/");
    } catch (err) {
      console.error("Login error:", err);
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
