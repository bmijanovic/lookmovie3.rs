import { createTheme } from "@mui/material/styles";

export const themeOptions = createTheme({
  palette: {
    primary: {
      main: '#606060',
      contrastText: 'white',
    },
    secondary: {
      main: '#ffffff',
    },
    negative: {
      main: "#cf142b",
      contrastText: 'white',
    },
    background: {
      default: "#000000", 
    },
    text: {
      primary: "#ffffff", // Set primary text color
      secondary: "#aaaaaa", // Set secondary text color
    },
  },
  typography: {
    button: {
      fontSize: 18,
      color: "#ffffff", // Set button text color
    },
    fontFamily: 'sans-serif',
    fontSize: 12,
    allVariants: {
      color: '#ffffff', // Set default color for all text variants
    },
  },
});
