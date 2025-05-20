import React from "react";
import MainLayout from "../../components/Layout/MainLayout";
import Register from "../../components/LoginAndRegister/Register";
import Login from "../../components/LoginAndRegister/Login";
type Props = {};

const LoginPage = (props: Props) => {
      return (
              <div>
                    <MainLayout>
                          <Login/>
                    </MainLayout>
              </div>
      );
};

export default LoginPage;
