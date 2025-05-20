import React from "react";
import MainRoutes from "./MainRoutes";
import { createTheme, ThemeProvider } from "@mui/material/styles";
import AuthProvider from "./App/auth/authProvider";

const theme = createTheme({
  palette: {
    secondary: {
      main: "#C53A3F",
      contrastText: "#fff",
    },
    primary: {
      main: "rgb(42, 85, 115,50%)",
      contrastText: "#fff",
    },
  },
});

function App() {
  //Map пока как комонент идет , потом в main добавил, это для удобста:)
  return (
    <ThemeProvider theme={theme}>
      <AuthProvider>
        <div className="App">
          <MainRoutes />
        </div>
      </AuthProvider>
    </ThemeProvider>
  );
}

export default App;
