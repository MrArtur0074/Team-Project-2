import React from "react";
import HeaderAdmin from "./HeaderAdmin";



const AdminLayout = ({ children }) => {
  return (
    <div>
      <HeaderAdmin />
      <main className="min-h-screen">{children}</main>
    </div>
  );
};

export default AdminLayout;
