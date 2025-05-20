import { useForm, FormProvider } from "react-hook-form";
import { yupResolver } from "@hookform/resolvers/yup";
import { LoginFormSchema } from "../../../helpers/validations";
import React, { useEffect, useRef, useState } from "react";
import ModalHeading from "../ModalHeading";
import TextField from "@mui/material/TextField";
import Button, { ButtonProps } from "@mui/material/Button/Button";
import { Link, useNavigate } from "react-router-dom";
import { Container, IconButton, InputAdornment } from "@mui/material";
import { Visibility, VisibilityOff } from "@mui/icons-material";
import { useAuth } from "../../../App/auth/authProvider";

function Login() {
  const auth = useAuth();
  const navigate = useNavigate();
  const passwordInputRef = useRef<HTMLInputElement>(null);
  const [showPassword, setShowPassword] = useState(false);

  const [input, setInput] = useState({
    username: "",
    password: "",
  });

  const [userExistence, setUserExistence] = useState("");

  const form = useForm({
    mode: "onChange",
    resolver: yupResolver(LoginFormSchema),
    defaultValues: {
      username: "",
      password: "",
    }
  });

  useEffect(() => {
    if (auth?.isAuthenticated) {
      navigate("/account");
    }
  }, [auth?.isAuthenticated]);

  async function handleSubmit(e: React.FormEvent<HTMLFormElement>): Promise<void> {
    e.preventDefault();

    if (input.username !== "" && input.password !== "" && auth && auth.login) {
      const loggedIn = await auth.login({username: input.username, password: input.password});
      if (loggedIn) {
        navigate("/account");
      } else {
        setUserExistence("Неверные учетные данные");
      }
    }
  };

  function handleInput(e: React.ChangeEvent<HTMLInputElement>): void {
    const {name, value} = e.target;
    setInput((prev) => ({
      ...prev,
      [name]: value,
    }));
  };

  const toggleVisibility = () => {
    setShowPassword(!showPassword);
  };

  return (
          <Container sx={{ maxWidth: "600px", padding: "20px" }}>
            <div className="w-[auto] pb-[50px]">
              <form onSubmit={handleSubmit}>
                <div className="flex flex-col justify-between h-[440px]">
                  <div className="flex flex-col md:mb-6">
                    <div className="mb-6">
                      <ModalHeading>Вход</ModalHeading>
                    </div>
                    <div className="grid gap-4">
                      <TextField
                              label="Имя пользователя"
                              id="username"
                              name="username"
                              size="small"
                              color="secondary"
                              value={input.username}
                              onChange={handleInput}
                              required
                              fullWidth
                      />
                      <TextField
                              label="Пароль"
                              id="password"
                              name="password"
                              size="small"
                              color="secondary"
                              type={showPassword ? "text" : "password"}
                              value={input.password}
                              onChange={handleInput}
                              required
                              fullWidth
                              inputRef={passwordInputRef}
                              InputProps={{
                                endAdornment: (
                                        <InputAdornment position="end">
                                          <IconButton
                                                  aria-label="toggle password visibility"
                                                  onClick={toggleVisibility}
                                                  edge="end"
                                          >
                                            {showPassword ? <VisibilityOff /> : <Visibility />}
                                          </IconButton>
                                        </InputAdornment>
                                ),
                              }}
                      />
                    </div>
                    {userExistence && (
                            <div className="text-red-500 text-xs mt-2">
                              {userExistence}
                            </div>
                    )}
                    <div className="flex justify-end mb-6">
                      <p className="text-xs underline cursor-pointer text-[#8E949A] mb-2 w-24">
                        Забыли пароль?
                      </p>
                    </div>
                    <Button
                            type="submit"
                            fullWidth
                            color="secondary"
                            variant="contained"
                            size="large"
                            sx={{ textTransform: "capitalize", borderRadius: 2 }}
                    >
                      Войти
                    </Button>
                  </div>
                  <div className="flex mx-auto flex-row justify-end text-xs cursor-default text-ourblue font-bold">
                    Нет аккаунта?
                    <p
                            onClick={() => navigate("/auth/register")}
                            className="text-ourred cursor-pointer ml-1"
                    >
                      Зарегистрироваться
                    </p>
                  </div>
                </div>
              </form>
            </div>
          </Container>
  );
}

export default Login;