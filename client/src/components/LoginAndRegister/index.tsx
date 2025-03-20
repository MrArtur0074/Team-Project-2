import React, { useState } from "react";
import Login from "./Login";
import Register from "./Register";
import ResetPassword from "./ResetPassword";
import authpic from "./assets/picauth.svg";

type Props = {};

function LoginAndRegister({}: Props) {
  const [formType, setFormType] = useState<
    "login" | "register" | "resetPassword"
  >("login");

  return (
    <>
      <div className="container  mx-auto flex flex-row justify-evenly p-0 mt-12">
        <img src={authpic} alt="" className="w-[50%] xl:w-[100%] hidden lg:flex" />
        <div className="flex items-center justify-center mx-auto">
          {formType == "login" && (
            <div  className="outline outline-2 outline-offset-0 outline-ourred rounded-2xl p-8 pb-10 sm:p-14 sm:w-[auto] w-[90%]">
              <Login setFormType={setFormType} />
            </div>
          )}
          {formType == "register" && (
            <div  className="outline outline-2 outline-offset-0 outline-ourred rounded-2xl p-8 pb-10 sm:p-14 sm:w-[auto] w-[90%]">
              <Register setFormType={setFormType} />
            </div>
          )}
          {formType == "resetPassword" && (
            <div  className="outline outline-2 outline-offset-0 outline-ourred rounded-2xl p-8 pb-10 sm:p-14 sm:w-[auto] w-[90%]">
              <ResetPassword setFormType={setFormType} />
            </div>
          )}
        </div>
      </div>
    </>
  );
}

export default LoginAndRegister;
