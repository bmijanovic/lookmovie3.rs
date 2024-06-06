import React from "react";
import { FormControl, InputLabel, Select, MenuItem } from "@mui/material";

const BasicSelect = ({
  label,
  callback,
  selected,
  collection,
  valueParam,
  nameParam,
  nameParam2 = "",
  visibility = "visible",
}) => {
  return (
    <FormControl
      sx={{
        alignItems: "center",
        m: 1,
        minWidth: 120,
        maxHeight: 40,
        marginTop: "20px",
        visibility: visibility,
        width: "20vw",
      }}
    >
      <Select
        sx={{backgroundColor: "#333"}}
        style={{ width: "200px" ,backgroundColor: "#333" }}
        id={label + "-select"}
        key={label + "-select"}
        value={selected ? selected : "x"}
        displayEmpty
        onChange={callback}
      >
        <MenuItem  sx = {{backgroundColor : "#333"}} value={"x"}>{"Select " + label}</MenuItem>

        {collection.map((item, index) => (
          <MenuItem sx = {{backgroundColor : "#333"}} key={index} value={item[valueParam]}>
            {item[nameParam] + " " + item[nameParam2]}
          </MenuItem>
        ))}
      </Select>
    </FormControl>
  );
};

export default BasicSelect;
