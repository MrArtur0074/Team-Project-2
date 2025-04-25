import React, { useEffect, useState } from "react";
import { useAuth } from "../../../context/AuthContextProvider";
import ModalHeading from "../ModalHeading";
import TextField from "@mui/material/TextField";
import Button from "@mui/material/Button";
import { styled } from "@mui/system";
import { MenuItem } from "@mui/material";
// import { useNavigate } from "react-router";
import {
  Formik,

  Form,

} from "formik";



// interface MyFormValues {
//   login: string;
//   password: string;
// }

const CssTextField = styled(TextField)({
  // "& label.Mui-focused": {
  //   color: "green",
  // },
  // "& .MuiInput-underline:after": {
  //   borderBottomColor: "green",
  // },
  "& .MuiOutlinedInput-root": {
    "& fieldset": {
      borderColor: "rgb(42, 85, 115,50%)",
    },
    "&:hover fieldset": {
      borderColor: "rgb(42, 85, 115,50%)",
    },
    // "&.Mui-focused fieldset": {
    //   borderColor: "green",
    // },
  },
});

const bloodType = [
  {
    value: 1,
    label: "О (I) Rh+",
  },
  {
    value: 2,
    label: "A (I) Rh+",
  },
  {
    value: 3,
    label: "B (I) Rh+",
  },
  {
    value: 4,
    label: "AB (I) Rh+",
  },

  {
    value: 5,
    label: "О (I) Rh-",
  },
  {
    value: 6,
    label: "A (I) Rh-",
  },
  {
    value: 7,
    label: "B (I) Rh-",
  },
  {
    value: 8,
    label: "AB (I) Rh-",
  },
];

const gender = [
  {
    value: 1,
    label: "Мужчина",
  },
  {
    value: 2,
    label: "Женщина",
  },
];


function Register({ setFormType }) {
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const [email, setEmail] = useState("");
  const [blood, setBlood] = useState(0);
  const [lastName, setLastName] = useState("");
  const [genderid, setGender] = useState(0);
  const [passwordConfirm, setPasswordConfirm] = useState("");
  const [selectedDate, setSelectedDate] = useState("");
  const formFields = {
    birthDate: selectedDate,
    bloodTypeId: blood,
    email,
    gender_id: genderid,
    name: username,
    password,
    surname: lastName,
  };
  
  const { register } = useAuth();
  useEffect(() => {
    console.log(blood);
  }, [blood]);

  // const navigate = useNavigate();

  // function createUser() {
  
    
  //   console.log(
  //     selectedDate,
  //     blood,
  //     email,
  //     genderid,
  //     username,
  //     password,
  //     lastName,
  //   );
  //   if (
  //     !email ||
  //     !genderid ||
  //     !password ||
  //     !passwordConfirm ||
  //     !username ||
  //     !blood ||
  //     !lastName ||
  //     !selectedDate
  //   ) {
  //     alert("You have empty inputs!");
  //     return;
  //   }

  //   // let formData = new FormData();
  //   // formData.append("birthDate", selectedDate);
  //   // formData.append("bloodTypeId", blood);
  //   // formData.append("email", email);
  //   // formData.append("gender_id", genderid);
  //   // formData.append("name", username);
  //   // formData.append("password", password);
  //   // formData.append("surname", lastName);
  //   let formData = new FormData();
  //   const formObj = {};
  //   formData.forEach((value, key) => {
  //     formObj[key] = typeof value;
  //   });
  //   console.log(formObj); // This should show the correct types now
  //   register(formData);
  // }
  function createUser() {
    // Convert gender_id and bloodTypeId to numbers before appending
    const numericFormFields = {
      birthDate: selectedDate,
      bloodTypeId: Number(blood),  // Convert to number
      email,
      gender_id: Number(genderid), // Convert to number
      name: username,
      password,
      surname: lastName,
    };
  
    let formData = new FormData();
    for (const key in numericFormFields) {
      if (numericFormFields[key] !== undefined && numericFormFields[key] !== null) {
        formData.append(key, numericFormFields[key]);
      }
    }
  
    // Log the object version of formData to check
    const formObj = {};
    formData.forEach((value, key) => {
      formObj[key] = value;
    });
    console.log(formObj); // This should show the correct types now
  
    register(formData);
  }

  const initialValues = {
    login: "",
    password: "",
  };

  function setFormLogin() {
    setFormType("login");
  }

  return (
    <div className="w-[auto] pb-[50px]">
      <ModalHeading>Регистрация</ModalHeading>
      <Formik
        // onSubmit={(values, actions) => {
          // console.log({ values, actions });
          // alert(JSON.stringify(values, null, 2));
          // actions.setSubmitting(false);
        // }}
        initialValues={initialValues}
      >
        <Form className="flex gap-[14px] flex-col mt-5">
          <TextField
            id="mail"
            label="Введите почту"
            variant="outlined"
            color="secondary"
            size="small"
            onChange={(e) => setEmail(e.target.value)}
          />
          <TextField
            id="name"
            label="Введите имя"
            variant="outlined"
            color="secondary"
            size="small"
            onChange={(e) => setUsername(e.target.value)}
          />
          <TextField
            id="lastname"
            label="Введите фамилию"
            variant="outlined"
            color="secondary"
            size="small"
            onChange={(e) => setLastName(e.target.value)}
          />
          <TextField
            id="date"
            type="date"
            label="Введите дату рождения пример 2000.0l.20"
            variant="outlined"
            color="secondary"
            size="small"
            onChange={(e) => setSelectedDate(e.target.value)}
          />
          <TextField
            id="outlined-select-currency"
            select
            label="Выберите свою гуппу крови"
            color="secondary"
            defaultValue=""
            size="small"
          >
            {bloodType.map((option) => (
              <MenuItem
                key={option.value}
                value={option.value}
                onClick={() => setBlood(option.value)}
                sx={{ background: "white" }}
              >
                {option.label}
              </MenuItem>
            ))}
          </TextField>
          <TextField
            id="outlined-select-currency"
            select
            label="Укажите пол"
            defaultValue=""
            color="secondary"
            size="small"
          >
            {gender.map((option) => (
              <MenuItem
                key={option.value}
                value={option.value}
                onClick={() => setGender(option.value)}
              >
                {option.label}
              </MenuItem>
            ))}
          </TextField>
          <TextField
            id="password"
            label="Введите пароль"
            variant="outlined"
            color="secondary"
            size="small"
            type="password"
            onChange={(e) => setPassword(e.target.value)}
          />
          <TextField
            id="password2"
            label="Повторите пароль"
            variant="outlined"
            color="secondary"
            size="small"
            type="password"
            onChange={(e) => setPasswordConfirm(e.target.value)}
          />

          <Button
            type="submit"
            color="secondary"
            variant="contained"
            sx={{
              textTransform: "capitalize",
              borderRadius: 2,
              height: "50px",
            }}
            onClick={createUser}
          >
            <p className="font-semibold text-base">Зарегистрироваться</p>
          </Button>

          <div className="flex justify-center">
            <p className="text-xs text-center  font-bold text-ourblue">
              Уже есть аккаунт?
            </p>
            <p
              className="text-xs text-center font-bold cursor-pointer text-ourred ml-1"
              onClick={setFormLogin}
            >
              Войти
            </p>
          </div>
        </Form>
      </Formik>
    </div>
  );
}

export default Register;
