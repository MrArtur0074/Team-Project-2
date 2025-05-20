import React, { useEffect, useRef, useState } from "react";
import { useNavigate } from "react-router-dom";
import {useAuth} from "../../../App/auth/authProvider";
import axios from "axios";

// Material UI imports
import TextField from "@mui/material/TextField";
import Button from "@mui/material/Button";
import { styled } from "@mui/system";
import { MenuItem, Container } from "@mui/material";
import { Form, Formik } from "formik";

// Icons (using Material UI icons instead of SVG imports)
import VisibilityOffIcon from "@mui/icons-material/VisibilityOff";
import VisibilityIcon from "@mui/icons-material/Visibility";
import ErrorOutlineIcon from "@mui/icons-material/ErrorOutline";

// Styling for text fields
const CssTextField = styled(TextField)({
  "& .MuiOutlinedInput-root": {
    "& fieldset": {
      borderColor: "rgb(42, 85, 115, 50%)",
    },
    "&:hover fieldset": {
      borderColor: "rgb(42, 85, 115, 50%)",
    },
  },
});

// Blood type options - modified to use label as value
const bloodType = [
  { value: "О (I) Rh+", label: "О (I) Rh+" },
  { value: "A (I) Rh+", label: "A (I) Rh+" },
  { value: "B (I) Rh+", label: "B (I) Rh+" },
  { value: "AB (I) Rh+", label: "AB (I) Rh+" },
  { value: "О (I) Rh-", label: "О (I) Rh-" },
  { value: "A (I) Rh-", label: "A (I) Rh-" },
  { value: "B (I) Rh-", label: "B (I) Rh-" },
  { value: "AB (I) Rh-", label: "AB (I) Rh-" },
];

// Gender options - modified to use label as value
const gender = [
  { value: "Мужчина", label: "Мужчина" },
  { value: "Женщина", label: "Женщина" },
];

// Type for registration data
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

export default function Register() {
  const auth = useAuth();
  const navigate = useNavigate();

  // Registration status state
  const [isRegisterSucceed, setIsRegisterSucceed] = useState<string>("");

  // Password visibility
  const [passwordType, setPasswordType] = useState("password");

  // Request state
  const [isRequestSent, setIsRequestSent] = useState(false);

  // Form validation state
  const [validated, setValidated] = useState(false);

  // Form fields states
  const [emailForm, setEmailForm] = useState<string>("");
  const [emailFormErrors, setEmailFormErrors] = useState<string[]>([]);

  const [usernameForm, setUsernameForm] = useState<string>("");
  const [usernameFormErrors, setUsernameFormErrors] = useState<string[]>([]);

  const [nameForm, setNameForm] = useState<string>("");
  const [nameFormErrors, setNameFormErrors] = useState<string[]>([]);

  const [surnameForm, setSurnameForm] = useState<string>("");
  const [surnameFormErrors, setSurnameFormErrors] = useState<string[]>([]);

  const [passwordForm, setPasswordForm] = useState<string>("");
  const [passwordFormErrors, setPasswordFormErrors] = useState<string[]>([]);

  const [repeatPasswordForm, setRepeatPasswordForm] = useState<string>("");
  const [repeatPasswordFormError, setRepeatPasswordFormError] = useState<string>("");

  // Additional fields from second form - updated to store string values instead of IDs
  const [bloodTypeValue, setBloodTypeValue] = useState<string>("");
  const [bloodTypeError, setBloodTypeError] = useState<string>("");

  const [genderValue, setGenderValue] = useState<string>("");
  const [genderError, setGenderError] = useState<string>("");

  const [dateOfBirth, setdateOfBirth] = useState<string>("");
  const [dateOfBirthError, setdateOfBirthError] = useState<string>("");

  const timeoutId = useRef<NodeJS.Timeout | null>(null);

  /**
   * Validate email
   * @param {string} email - User email
   * @return {Promise<boolean>} - Returns true if email is valid, otherwise false
   */
  async function validateEmail(email: string): Promise<boolean> {
    const errors = [];
    const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    if(!email.includes("@gmail.com")) {
      errors.push("Введите почту на gmail.com");
    }
    if (!emailRegex.test(email)) {
      errors.push("Введите действительную почту");
    }
    if (errors.length === 0) {
      if (timeoutId.current) {
        clearTimeout(timeoutId.current);
      }
      timeoutId.current = setTimeout(async () => {
        if (!isRequestSent) {
          const isUsernameTaken = await checkIfEmailIsTaken(email);
          if (isUsernameTaken) {
            errors.push("Данная почта уже используется");
            setEmailFormErrors(["Данная почта уже используется"]);
          }
        }
      }, 1500);
    }
    setEmailFormErrors(errors);
    return errors.length === 0;
  }

  /**
   * Handle email change
   * @param {React.ChangeEvent<HTMLInputElement>} e - Change event (input)
   */
  function handleEmailChange(e: React.ChangeEvent<HTMLInputElement>): void {
    setEmailForm(e.target.value);
    validateEmail(e.target.value);
  }

  /**
   * Check if email is taken
   * @param {string} email - User email
   * @return {Promise<boolean>} - Returns true if email exists, otherwise false
   */
  async function checkIfEmailIsTaken(email: string): Promise<boolean> {
    try {
      const userRequest = await axios.post(
              `http://127.0.0.1:8080/auth/check-email`,
              { email: email },
              {
                headers: {
                  "Content-Type": "application/json",
                },
              }
      );
      return !userRequest.data;
    } catch {
      return true;
    }
  }

  /**
   * Validate username
   * @param {string} username - Username
   * @return {Promise<boolean>} - Returns true if username is valid, otherwise false
   */
  async function validateUsername(username: string): Promise<boolean> {
    const errors = [];
    if (username.length == 0) {
      errors.push("Введите имя пользователя");
    }
    if (username.length > 16) {
      errors.push("Имя пользователя не должно быть больше 16 символов");
    }
    if (/[^a-zA-Z0-9]/.test(username)) {
      errors.push("Используйте латиницу");
    }

    if (errors.length === 0) {
      if (timeoutId.current) {
        clearTimeout(timeoutId.current);
      }
      timeoutId.current = setTimeout(async () => {
        if (isRequestSent === false) {
          const isUsernameTaken = await checkIfUsernameIsTaken(username);
          if (isUsernameTaken == true) {
            errors.push("Username is already taken");
            setUsernameFormErrors(["Username is already taken"]);
          }
        }
      }, 1500);
    }

    setUsernameFormErrors(errors);
    return errors.length === 0;
  }

  /**
   * Handle username change
   * @param {React.ChangeEvent<HTMLInputElement>} e - Change event (input)
   */
  function handleUsernameChange(e: React.ChangeEvent<HTMLInputElement>): void {
    setUsernameForm(e.target.value);
    validateUsername(e.target.value);
  }

  /**
   * Check if username is taken
   * @param {string} username - Username
   * @return {Promise<boolean>} - Returns true if username exists, otherwise false
   */
  async function checkIfUsernameIsTaken(username: string): Promise<boolean> {
    try {
      const userRequest = await axios.post(
              `http://127.0.0.1:8080/auth/check-username`,
              { username: username },
              {
                headers: {
                  "Content-Type": "application/json",
                },
              }
      );
      return !userRequest.data;
    } catch {
      return true;
    }
  }

  /**
   * Validate name
   * @param {string} name - User name
   * @return {boolean} - Returns true if name is valid, otherwise false
   */
  function validateName(name: string): boolean {
    const errors = [];

    if (name.length === 0) {
      errors.push("Введите имя");
    }
    if (name.length > 16) {
      errors.push("Name should be less than 16 characters long");
    }

    if (/[^a-zA-Zа-яА-Я]/.test(name)) {
      errors.push("Only Latin letters are allowed");
    }

    setNameFormErrors(errors);
    return errors.length === 0;
  }

  /**
   * Handle name change
   * @param {React.ChangeEvent<HTMLInputElement>} e - Change event (input)
   */
  function handleNameChange(e: React.ChangeEvent<HTMLInputElement>): void {
    setNameForm(e.target.value);
    validateName(e.target.value);
  }

  /**
   * Validate surname
   * @param {string} surname - User surname
   * @return {boolean} - Returns true if surname is valid, otherwise false
   */
  function validateSurname(surname: string): boolean {
    const errors = [];

    if (surname.length === 0) {
      errors.push("Введите фамилию");
    }
    if (surname.length > 20) {
      errors.push("Фамилия не должна превышать 20 символов");
    }

    setSurnameFormErrors(errors);
    return errors.length === 0;
  }

  /**
   * Handle surname change
   * @param {React.ChangeEvent<HTMLInputElement>} e - Change event (input)
   */
  function handleSurnameChange(e: React.ChangeEvent<HTMLInputElement>): void {
    setSurnameForm(e.target.value);
    validateSurname(e.target.value);
  }

  /**
   * Validate password
   * @param {string} password - User password
   * @return {boolean} - Returns true if password is valid, otherwise false
   */
  function validatePassword(password: string): boolean {
    const errors = [];

    if (password.length < 8) {
      errors.push("Парольд должен быть не менее 8 символов");
    }
    if (password.length > 30) {
      errors.push("Пароль должен быть не более 30 символов");
    }
    if (!/[A-Z]/.test(password)) {
      errors.push("Пароль должен содержать 1 заглавную букву");
    }

    if (!/[a-z]/.test(password)) {
      errors.push("Пароль должен содержать 1 строчную букву");
    }

    if (!/[0-9]/.test(password)) {
      errors.push("Пароль должен содержать 1 цифру");
    }

    setPasswordFormErrors(errors);
    return errors.length === 0;
  }

  /**
   * Handle password change
   * @param {React.ChangeEvent<HTMLInputElement>} e - Change event (input)
   */
  function handlePasswordChange(e: React.ChangeEvent<HTMLInputElement>): void {
    setPasswordForm(e.target.value);
    validatePassword(e.target.value);
  }

  /**
   * Validate repeat password
   * @param {string} password - User password
   */
  function validateRepeatPassword(password: string): boolean {
    if (password !== passwordForm) {
      setRepeatPasswordFormError("Пароли не совпадают");
      return false;
    }
    setRepeatPasswordFormError("");
    return true;
  }

  /**
   * Handle repeat password change
   * @param {React.ChangeEvent<HTMLInputElement>} e - Change event (input)
   */
  function handleRepeatPasswordChange(e: React.ChangeEvent<HTMLInputElement>): void {
    setRepeatPasswordForm(e.target.value);
    validateRepeatPassword(e.target.value);
  }

  /**
   * Validate birth date
   * @param {string} date - Birth date
   */
  function validatedateOfBirth(date: string): boolean {
    if (!date) {
      setdateOfBirthError("Введите дату рождения");
      return false;
    }
    // Validate date format YYYY.MM.DD
    const dateRegex = /^\d{4}\.\d{2}\.\d{2}$/;
    if (!dateRegex.test(date)) {
      setdateOfBirthError("Формат даты рождения ГОД.МЕСЯЦ.ДЕНЬ");
      return false;
    }
    setdateOfBirthError("");
    return true;
  }

  /**
   * Handle birth date change
   * @param {React.ChangeEvent<HTMLInputElement>} e - Change event (input)
   */
  function handledateOfBirthChange(e: React.ChangeEvent<HTMLInputElement>): void {
    setdateOfBirth(e.target.value);
    validatedateOfBirth(e.target.value);
  }

  /**
   * Validate blood type
   * @param {string} bloodType - Blood type
   */
  function validateBloodType(bloodType: string): boolean {
    if (!bloodType) {
      setBloodTypeError("Select your blood type");
      return false;
    }
    setBloodTypeError("");
    return true;
  }

  /**
   * Handle blood type change
   * @param {React.ChangeEvent<HTMLInputElement>} e - Change event (input)
   */
  function handleBloodTypeChange(e: React.ChangeEvent<HTMLInputElement>): void {
    setBloodTypeValue(e.target.value);
    validateBloodType(e.target.value);
  }

  /**
   * Validate gender
   * @param {string} gender - Gender
   */
  function validateGender(gender: string): boolean {
    if (!gender) {
      setGenderError("Select your gender");
      return false;
    }
    setGenderError("");
    return true;
  }

  /**
   * Handle gender change
   * @param {React.ChangeEvent<HTMLInputElement>} e - Change event (input)
   */
  function handleGenderChange(e: React.ChangeEvent<HTMLInputElement>): void {
    setGenderValue(e.target.value);
    validateGender(e.target.value);
  }

  /**
   * Toggle password visibility
   */
  function toggleVisibility(): void {
    if (passwordType === "password") {
      setPasswordType("text");
    } else {
      setPasswordType("password");
    }
  }

  /**
   * Redirect to Profile page if user is already logged in
   */
  useEffect(() => {
    if (auth?.isAuthenticated) {
      navigate("/account");
    }
  }, [auth?.isAuthenticated]);

  /**
   * Submit form handler
   * @param {React.FormEvent<HTMLFormElement>} event - Form submit event
   */
  async function handleSubmit(event: React.FormEvent<HTMLFormElement>): Promise<void> {
    event.preventDefault();
    const formData = new FormData(event.currentTarget);

    const isEmailValid = await validateEmail(emailForm);
    const isUsernameValid = await validateUsername(usernameForm);
    const isNameValid = validateName(nameForm);
    const isSurnameValid = validateSurname(surnameForm);
    const isPasswordValid = validatePassword(passwordForm);
    const isRepeatPasswordValid = validateRepeatPassword(repeatPasswordForm);
    const isdateOfBirthValid = validatedateOfBirth(dateOfBirth);
    const isBloodTypeValid = validateBloodType(bloodTypeValue);
    const isGenderValid = validateGender(genderValue);

    const dataToSubmit: RegistrationData = {
      username: usernameForm,
      email: emailForm,
      surname: surnameForm,
      name: nameForm,
      password: passwordForm,
      dateOfBirth: dateOfBirth,
      bloodType: bloodTypeValue,
      gender: genderValue,
    };

    if (
            isEmailValid &&
            isUsernameValid &&
            isNameValid &&
            isSurnameValid &&
            isPasswordValid &&
            isRepeatPasswordValid &&
            isdateOfBirthValid &&
            isBloodTypeValid &&
            isGenderValid &&
            auth &&
            auth.register
    ) {
      const loggedIn = await auth.register(dataToSubmit);
      if (loggedIn) {
        navigate("/account");
      } else {
        setIsRequestSent(true);
        setIsRegisterSucceed("Ошибка регистрации, попробуйте позже");
      }
    }
  }

  return (
          <Container sx={{ maxWidth: "600px", padding: "20px" }}>
            <div className="w-[auto] pb-[50px]">
              <h1 className="text-center text-2xl font-bold mb-4">Registration</h1>
              <Formik
                      initialValues={{}}
                      onSubmit={() => {}}
              >
                <Form className="flex gap-[14px] flex-col mt-5" onSubmit={handleSubmit}>
                  <CssTextField
                          id="email"
                          label="Введите почту"
                          variant="outlined"
                          color="primary"
                          size="small"
                          name="email"
                          value={emailForm}
                          onChange={handleEmailChange}
                          error={emailFormErrors.length > 0}
                          helperText={
                                  emailFormErrors.length > 0 && (
                                          <div>
                                            {emailFormErrors.map((error, index) => (
                                                    <div key={index} className="flex items-center gap-1">
                                                      <ErrorOutlineIcon fontSize="small" />
                                                      <span>{error}</span>
                                                    </div>
                                            ))}
                                          </div>
                                  )
                          }
                  />

                  <CssTextField
                          id="username"
                          label="Введите имя пользователя"
                          variant="outlined"
                          color="primary"
                          size="small"
                          name="username"
                          value={usernameForm}
                          onChange={handleUsernameChange}
                          error={usernameFormErrors.length > 0}
                          helperText={
                                  usernameFormErrors.length > 0 && (
                                          <div>
                                            {usernameFormErrors.map((error, index) => (
                                                    <div key={index} className="flex items-center gap-1">
                                                      <ErrorOutlineIcon fontSize="small" />
                                                      <span>{error}</span>
                                                    </div>
                                            ))}
                                          </div>
                                  )
                          }
                  />

                  <CssTextField
                          id="name"
                          label="Введите имя"
                          variant="outlined"
                          color="primary"
                          size="small"
                          name="name"
                          value={nameForm}
                          onChange={handleNameChange}
                          error={nameFormErrors.length > 0}
                          helperText={
                                  nameFormErrors.length > 0 && (
                                          <div>
                                            {nameFormErrors.map((error, index) => (
                                                    <div key={index} className="flex items-center gap-1">
                                                      <ErrorOutlineIcon fontSize="small" />
                                                      <span>{error}</span>
                                                    </div>
                                            ))}
                                          </div>
                                  )
                          }
                  />

                  <CssTextField
                          id="surname"
                          label="Введите фамилию"
                          variant="outlined"
                          color="primary"
                          size="small"
                          name="surname"
                          value={surnameForm}
                          onChange={handleSurnameChange}
                          error={surnameFormErrors.length > 0}
                          helperText={
                                  surnameFormErrors.length > 0 && (
                                          <div>
                                            {surnameFormErrors.map((error, index) => (
                                                    <div key={index} className="flex items-center gap-1">
                                                      <ErrorOutlineIcon fontSize="small" />
                                                      <span>{error}</span>
                                                    </div>
                                            ))}
                                          </div>
                                  )
                          }
                  />

                  <CssTextField
                          id="dateOfBirth"
                          label="Введите дату рождения(ГОД.МЕСЯЦ.ДЕНЬ)"
                          variant="outlined"
                          color="primary"
                          size="small"
                          name="dateOfBirth"
                          value={dateOfBirth}
                          onChange={handledateOfBirthChange}
                          error={!!dateOfBirthError}
                          helperText={
                                  dateOfBirthError && (
                                          <div className="flex items-center gap-1">
                                            <ErrorOutlineIcon fontSize="small" />
                                            <span>{dateOfBirthError}</span>
                                          </div>
                                  )
                          }
                  />

                  <CssTextField
                          id="bloodType"
                          select
                          label="Выберите группу крови"
                          variant="outlined"
                          color="primary"
                          size="small"
                          name="bloodType"
                          value={bloodTypeValue}
                          onChange={handleBloodTypeChange}
                          error={!!bloodTypeError}
                          helperText={
                                  bloodTypeError && (
                                          <div className="flex items-center gap-1">
                                            <ErrorOutlineIcon fontSize="small" />
                                            <span>{bloodTypeError}</span>
                                          </div>
                                  )
                          }
                  >
                    {bloodType.map((option) => (
                            <MenuItem key={option.value} value={option.value}>
                              {option.label}
                            </MenuItem>
                    ))}
                  </CssTextField>

                  <CssTextField
                          id="gender"
                          select
                          label="Укажите пол"
                          variant="outlined"
                          color="primary"
                          size="small"
                          name="gender"
                          value={genderValue}
                          onChange={handleGenderChange}
                          error={!!genderError}
                          helperText={
                                  genderError && (
                                          <div className="flex items-center gap-1">
                                            <ErrorOutlineIcon fontSize="small" />
                                            <span>{genderError}</span>
                                          </div>
                                  )
                          }
                  >
                    {gender.map((option) => (
                            <MenuItem key={option.value} value={option.value}>
                              {option.label}
                            </MenuItem>
                    ))}
                  </CssTextField>

                  <div className="relative">
                    <CssTextField
                            id="password"
                            label="Введите пароль"
                            variant="outlined"
                            color="primary"
                            size="small"
                            type={passwordType}
                            name="password"
                            value={passwordForm}
                            onChange={handlePasswordChange}
                            fullWidth
                            error={passwordFormErrors.length > 0}
                            helperText={
                                    passwordFormErrors.length > 0 && (
                                            <div>
                                              {passwordFormErrors.map((error, index) => (
                                                      <div key={index} className="flex items-center gap-1">
                                                        <ErrorOutlineIcon fontSize="small" />
                                                        <span>{error}</span>
                                                      </div>
                                              ))}
                                            </div>
                                    )
                            }
                    />
                    <div
                            className="absolute right-2 top-3 cursor-pointer"
                            onClick={toggleVisibility}
                    >
                      {passwordType === "password" ? (
                              <VisibilityOffIcon fontSize="small" />
                      ) : (
                              <VisibilityIcon fontSize="small" />
                      )}
                    </div>
                  </div>

                  <div className="relative">
                    <CssTextField
                            id="repeatPassword"
                            label="Повторите пароль"
                            variant="outlined"
                            color="primary"
                            size="small"
                            type={passwordType}
                            name="repeatPassword"
                            value={repeatPasswordForm}
                            onChange={handleRepeatPasswordChange}
                            fullWidth
                            error={!!repeatPasswordFormError}
                            helperText={
                                    repeatPasswordFormError && (
                                            <div className="flex items-center gap-1">
                                              <ErrorOutlineIcon fontSize="small" />
                                              <span>{repeatPasswordFormError}</span>
                                            </div>
                                    )
                            }
                    />
                    <div
                            className="absolute right-2 top-3 cursor-pointer"
                            onClick={toggleVisibility}
                    >
                      {passwordType === "password" ? (
                              <VisibilityOffIcon fontSize="small" />
                      ) : (
                              <VisibilityIcon fontSize="small" />
                      )}
                    </div>
                  </div>

                  {isRegisterSucceed && (
                          <div className="text-red-500 text-center">{isRegisterSucceed}</div>
                  )}

                  <Button
                          type="submit"
                          color="primary"
                          variant="contained"
                          sx={{
                            textTransform: "capitalize",
                            borderRadius: 2,
                            height: "50px",
                          }}
                  >
                    <p className="font-semibold text-base">Зарегистрироваться</p>
                  </Button>

                  <div className="flex justify-center">
                    <p className="text-xs text-center font-bold">
                      Уже есть аккаунт?{" "}
                      <span onClick={() => navigate("/auth/login")}
                              className="text-blue-600 cursor-pointer ml-1"
                      >
                        Войти
                      </span>
                    </p>
                  </div>
                </Form>
              </Formik>
            </div>
          </Container>
  );
}