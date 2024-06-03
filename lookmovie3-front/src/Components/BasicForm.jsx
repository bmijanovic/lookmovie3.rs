import React, { useEffect, useState } from "react";
import FormComponent from "./FormComponents";
import { FormControl, Form } from "@mui/material";
import BasicButton from "./BasicButton";
import { maxWidth, minWidth } from "@mui/system";
const BasicForm = ({ template, callback, label,buttonName }) => {
  const [formState, setFormState] = useState({});

  const handleInputChange = (itemName, value) => {
    console.log(itemName);
    setFormState((prevFormState) => ({
      ...prevFormState,
      [itemName]: value,
    }));
  };
  return (
    <div
      style={{
        display: "flex",
        justifyContent: "center",
        alignItems: "center",
        flexDirection: "column",
      }}
    >
        <h1>{label}</h1>
      {template.map((item, index) => (
        <FormComponent
          key={index}
          props={item}
          form={formState}
          change={handleInputChange}
        />
      ))}
      <BasicButton
        text={buttonName}
        onClick={() => {
          callback(formState);
        }}
      />
    </div>
  );
};

export default BasicForm;
