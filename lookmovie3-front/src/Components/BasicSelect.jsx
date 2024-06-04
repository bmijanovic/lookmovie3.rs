import React from "react";
import { FormControl, InputLabel, Select, MenuItem } from "@mui/material";

const BasicSelect = ({
  label,
  callback,
  selected,
  collection,
  valueParam,
  nameParam,
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
        sx={{ marginTop: "20px" }}
        style={{ width: "200px" }}
        id={label + "-select"}
        key={label + "-select"}
        value={selected ? selected : "x"}
        displayEmpty
        onChange={callback}
      >
        <MenuItem value={"x"}>{"Select " + label}</MenuItem>

        {collection.map((item, index) => (
          <MenuItem key={index} value={item[valueParam]}>
            {item[nameParam]}
          </MenuItem>
        ))}
      </Select>
    </FormControl>
  );
};

export default BasicSelect;
