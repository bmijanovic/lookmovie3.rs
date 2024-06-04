import React from "react";
import { useState, useEffect } from "react";
import BasicForm from "../Components/BasicForm";
import Dialog from '@mui/material/Dialog';
import DialogTitle from '@mui/material/DialogTitle';
import DialogContent from '@mui/material/DialogContent';
import DialogActions from '@mui/material/DialogActions';
import Button from '@mui/material/Button';
import { API_BASE_URL } from "../App";
import axios from "axios";
import { Card, CardContent, CardActions } from "@mui/material";
import { useNavigate } from "react-router-dom";
const loginTemplate = [
    {
        item: "BasicInput",
        label: "E-mail",
        itemValue: "email"
    },
    {
        item: "BasicInput",
        label: "Password",
        itemValue: "password",
        type: "password"
    }
];

const LoginComponent = () => {
    const navigate = useNavigate();
    const [userCredentials, setUserCredentials] = useState({});
    const [dialogOpen, setDialogOpen] = useState(false);
    const [dialogTitle, setDialogTitle] = useState("");
    const [dialogContent, setDialogContent] = useState("");

    useEffect(() => {
        axios.get(`${API_BASE_URL}/auth/me`, {withCredentials: true})
        .then((response) => {
            console.log(response);
            navigate('/'); 

        })
        .catch((error) => {
            console.log(error);

        });
    }, []);

    useEffect(() => {
        console.log("Updated userCredentials:", userCredentials);
    }, [userCredentials]);

    const handleCloseDialog = () => {
        setDialogOpen(false);
    };

    const handleSubmit = async (state) => {
        setUserCredentials(state);
        axios.post(`${API_BASE_URL}/auth/login`, state, {
            withCredentials: true,
        }).then((response) => {
            console.log(response);
            navigate("/");
        }).catch((error) => {
            console.log(error);
        });
    };

    return (
        <div>
            <Card sx={{ maxWidth: 400, margin: "0 auto", padding: "10px", backgroundColor: "#333", color: "white" }}>
                <CardContent>
                    <BasicForm label="Lookmovie3.rs" buttonName = "Login" template={loginTemplate} callback={handleSubmit} />
                </CardContent>
                
            </Card>
            <Dialog open={dialogOpen} onClose={handleCloseDialog}>
                <DialogTitle>{dialogTitle}</DialogTitle>
                <DialogContent>{dialogContent}</DialogContent>
                <DialogActions>
                    <Button onClick={handleCloseDialog} color="primary">
                        Close
                    </Button>
                </DialogActions>
            </Dialog>
        </div>
    );
};

export default LoginComponent;
