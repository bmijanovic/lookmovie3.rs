import React from 'react';
import Dialog from '@mui/material/Dialog';
import DialogTitle from '@mui/material/DialogTitle';
import DialogContent from '@mui/material/DialogContent';
import DialogActions from '@mui/material/DialogActions';
import Button from '@mui/material/Button';

const InfoDialog = ({ open, onClose, title, content }) => {
  return (
    <Dialog open={open} onClose={onClose}>
      <DialogTitle  sx = {{backgroundColor:"#333"}}>{title}</DialogTitle>
      <DialogContent sx = {{backgroundColor:"#333"}}>{content}</DialogContent>
      
    </Dialog>
  );
};

export default InfoDialog;
