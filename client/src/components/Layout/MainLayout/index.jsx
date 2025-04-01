import React from "react";
import Header from "./Header/index";
import Footer from "./Footer";
import { BrowserRouter } from "react-router-dom";
import AdminHeader from "../AdminLayout/AdminHeader";



function MainLayout({ children }) {
  return (
    <>
      
      <Header />
      <main className="min-h-screen">{children}</main>
      <Footer />
    </>
  );
}

export default MainLayout;
