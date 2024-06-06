import React from "react";
import { Button } from "@mui/material";

const BasicButton = ({ color, text, onClick, variant="contained" }) => {
    return (
        <Button
            sx = {{ marginTop: "20px"}}
            variant={variant}
            color={color}
            onClick={onClick}
        >
        {text}
        </Button>
    );
};

export default BasicButton;