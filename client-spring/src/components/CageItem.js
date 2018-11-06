import React from "react";
import { Link } from "react-router-dom";

import { IconButton, ListItem, ListItemText, ListItemSecondaryAction } from "@material-ui/core";
import { Delete as DeleteIcon, Lock as LockIcon, Edit as EditIcon } from "@material-ui/icons";

const CageItem = ({ editFunc, deleteFunc, cage }) => (
  <ListItem key={cage.id} button component={Link} to={`/cages/${cage.id}/animals`}>
    <LockIcon />
    <ListItemText primary={cage.name} secondary={"Location: " + cage.location} />
    <ListItemSecondaryAction>
      <IconButton onClick={() => editFunc(cage)} color="inherit">
        <DeleteIcon />
      </IconButton>
      <IconButton onClick={() => deleteFunc(cage)} color="inherit">
        <EditIcon />
      </IconButton>
    </ListItemSecondaryAction>
  </ListItem>
);

export default CageItem;
