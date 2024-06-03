import React from "react";
import { TextField } from "@mui/material";

const BasicInput = ({ type, label, callback, value, visibility = 'visible' }) => {
  return (
    <div>
      <TextField
        sx={{
            marginTop: "20px",
          width: "100%",
          "& .MuiInputBase-root": {
            color: "#A0A0A0", // Text color
          },
          "& .MuiInputLabel-root": {
            color: "#A0A0A0", // Label color
          },
          "& .MuiOutlinedInput-root": {
            "& fieldset": {
              borderColor: "#A0A0A0", // Default border color
            },
            "&:hover fieldset": {
              borderColor: "#A0A0A0", // Border color when hovered
            },
            "&.Mui-focused fieldset": {
              borderColor: "#A0A0A0", // Border color when focused
            },
          },
        }}
        id={label}
        label={label}
        onChange={callback}
        visibility={visibility}
        type={type}
        value={value ? value : ""}
      />
    </div>
  );
};

export default BasicInput;
