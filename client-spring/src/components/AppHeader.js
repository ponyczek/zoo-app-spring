import React from "react";
import { Link } from "react-router-dom";
import { AppBar, Button, Toolbar, Typography, withStyles } from "@material-ui/core";

const styles = {
  flex: {
    flex: 1
  }
};

const AppHeader = ({ classes }) => (
  <AppBar position="static">
    <Toolbar>
      <Typography variant="title" color="inherit">
        Zoo App
      </Typography>
      <Button color="inherit" component={Link} to="/cages">
        Cages Manager
      </Button>
      <div className={classes.flex} />
    </Toolbar>
  </AppBar>
);

export default withStyles(styles)(AppHeader);
