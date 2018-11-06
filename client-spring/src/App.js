import React, { Fragment } from "react";
import { Route } from "react-router-dom";
import { CssBaseline, withStyles } from "@material-ui/core";
import "react-toastify/dist/ReactToastify.min.css";

import AppHeader from "./components/AppHeader";
import Home from "./pages/Home";
import CagesManager from "./pages/CagesManager";
import Cage from "./pages/Cage";

const styles = theme => ({
  main: {
    padding: 3 * theme.spacing.unit,
    [theme.breakpoints.down("xs")]: {
      padding: 2 * theme.spacing.unit
    }
  }
});

const App = ({ classes }) => (
  <Fragment>
    <CssBaseline />
    <AppHeader />
    <main className={classes.main}>
      <Route exact path="/" component={Home} />
      <Route exact path="/cages" component={CagesManager} />
      <Route exact path="/cages/:id/animals" component={Cage} />
    </main>
  </Fragment>
);

export default withStyles(styles)(App);
