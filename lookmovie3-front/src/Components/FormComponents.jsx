import React, { useEffect, useState } from "react";
import BasicSelect from "./BasicSelect";
import BasicInput from "./BasicInput";
import BasicRadioButton from "./BasicRadioButton";
import Dropzone from "react-dropzone";
import { Box, Typography } from "@mui/material";

const FormComponent = ({ props, form, change }) => {
  const [image, setImageBase64] = useState(null);

  useEffect(() => {}, [props, form]);

  const handleImageDrop = (acceptedFiles) => {
    const file = acceptedFiles[0];
    const reader = new FileReader();

    reader.onload = () => {
      const base64String = reader.result;
      setImageBase64(base64String);
      change("image", base64String);
    };

    reader.readAsDataURL(file);
  };

  switch (props.item) {
    case "BasicSelect":
      return (
        <BasicSelect
          label={props.label}
          collection={props.collection}
          valueParam={props.valueParam}
          selected={form[props.itemValue]}
          nameParam={props.nameParam}
          nameParam2={props.nameParam2}
          callback={(e) => change(props.itemValue, e.target.value)}
        />
      );
    case "BasicInput":
      return (
        <BasicInput
          label={props.label}
          type={props.type}
          value={form[props.itemValue]}
          callback={(e) => change(props.itemValue, e.target.value)}
        />
      );
    case "BasicRadio":
      return (
        <BasicRadioButton
          label={props.label}
          value={form[props.itemValue]}
          callback={(e) => change(props.itemValue, e.target.value)}
          choices={props.choices}
          identificator={props.identificator}
        />
      );
    case "ImageDrop":
      return (
        <Dropzone onDrop={handleImageDrop}>
          {({ getRootProps, getInputProps }) => (
            <Box
              {...getRootProps()}
              sx={{
                border: "2px dashed #cccccc",
                borderRadius: "8px",
                cursor: "pointer",
                padding: "20px",
                marginTop: "20px",
                width: "150px",
                height: "150px",
                display: "flex",
                alignItems: "center",
                justifyContent: "center",
                overflow: "hidden",
              }}
            >
              <input {...getInputProps()} />
              {image ? (
                <img
                  src={image}
                  alt="Uploaded Image"
                  style={{ width: "100%", height: "100%", objectFit: "cover" }}
                />
              ) : (
                <Typography>Click here or drag an image to upload.</Typography>
              )}
            </Box>
          )}
        </Dropzone>
      );
    
    default:
      return null;
  }
};

export default FormComponent;
