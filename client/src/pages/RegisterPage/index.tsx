import React from "react";
import MainLayout from "../../components/Layout/MainLayout";
import Register from "../../components/LoginAndRegister/Register";

type Props = {};

const RegisterPage = (props: Props) => {
      return (
              <div>
                    <MainLayout>
                          <Register/>
                    </MainLayout>
              </div>
      );
};

export default RegisterPage;
