import React, { useEffect, useState } from "react";
import axios from "axios";
import { API_BASE_URL } from "../App";

const ActorInfoComponent = ({ actor }) => {
  const [awards, setAwards] = useState({});

  useEffect(() => {
    axios
      .get(`${API_BASE_URL}/films/main-actor-info/${actor}`, { withCredentials: true })
      .then((response) => {
        setAwards(response.data);
        console.log(response.data);
      })
      .catch((error) => {
        console.log(error);
      });
  }, [actor]);

  return (
    <div>
      <h3>Awards</h3>
      <ul style={{paddingLeft:'15px'}}>
        {Object.entries(awards).map(([award, count]) => (
          <li key={award} >
            {award}: {count}
          </li>
        ))}
      </ul>
    </div>
  );
};

export default ActorInfoComponent;
