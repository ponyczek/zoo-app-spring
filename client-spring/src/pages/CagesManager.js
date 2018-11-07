import React, { Component, Fragment } from "react";
import { withRouter } from "react-router-dom";
import {
  withStyles,
  Typography,
  Button,
  Paper,
  List,
  Dialog,
  TextField,
  DialogActions,
  DialogContent,
  DialogContentText,
  DialogTitle
} from "@material-ui/core";
import { Add as AddIcon } from "@material-ui/icons";
import { orderBy } from "lodash";
import { compose } from "recompose";
import CageItem from "../components/CageItem";
import { ToastContainer, toast } from "react-toastify";

const styles = theme => ({
  cages: {
    marginTop: 2 * theme.spacing.unit
  },
  fab: {
    position: "absolute",
    bottom: 3 * theme.spacing.unit,
    right: 3 * theme.spacing.unit,
    [theme.breakpoints.down("xs")]: {
      bottom: 2 * theme.spacing.unit,
      right: 2 * theme.spacing.unit
    }
  }
});

const toastSetup = {
  position: "top-right",
  autoClose: 4000,
  hideProgressBar: true,
  closeOnClick: true,
  pauseOnHover: true,
  draggable: true
};

const API = process.env.REACT_APP_API || "http://localhost:8080/api/";

class CagesManager extends Component {
  state = {
    loading: true,
    cages: [],
    openModal: false,
    name: "",
    location: "",
    cage: {},
    edit: false,
    fieldError: "Field can't be blank."
  };

  handleClickOpen = () => {
    this.setState({ openModal: true, edit: false, cage: {}, name: "", location: "" });
  };

  handleClose = () => {
    this.setState({ openModal: false });
  };

  handlePutOrPost(name, location, method, endpoint) {
    if (name && location) {
      let id = this.state.cage.id;
      let data = JSON.stringify({ name, location, id });

      fetch(API + endpoint, {
        method: method,
        body: data,
        headers: {
          Accept: "application/json, text/plain, */*",
          "Content-Type": "application/json"
        }
      }).then(
        resp => {
          toast.success(
            `The cage has been ${method === "POST" ? "added" : "updated"} successfully.`,
            toastSetup
          );

          this.getCages();
          this.setState({ openModal: false });
        },
        err => {
          toast.error("Something went wrong", toastSetup);
        }
      );
    }
  }

  handleSubmit(edit, name, location) {
    let method = edit ? "PUT" : "POST";
    let id = this.state.cage.id;
    let endpoint = edit ? `cages/${id}` : "cages";
    this.handlePutOrPost(name, location, method, endpoint);
  }

  handleNameChange = e => {
    this.setState({ name: e.target.value });
  };

  handleLocationChange = e => {
    this.setState({ location: e.target.value });
  };

  componentDidMount() {
    this.getCages();
  }

  async fetch(method, endpoint, body) {
    try {
      const response = await fetch(`${API}${endpoint}`, {
        method,
        body: body && JSON.stringify(body),
        headers: {
          "content-type": "application/json",
          accept: "application/json"
        }
      });
      return (await method) === "get" ? response.json() : response.status;
    } catch (error) {
      console.error(error);
    }
  }

  async getCages() {
    this.setState({ loading: false, cages: await this.fetch("get", "/cages") });
  }

  async deleteCage(cage) {
    if (window.confirm(`Are you sure you want to delete ${cage.name} and all animals inside?"`)) {
      await this.fetch("delete", `cages/${cage.id}`).then(
        resp => {
          toast.success("Cage and all the animals 🦄 in the cage have been removed.", toastSetup);
        },
        err => {
          toast.error("Something went wrong", toastSetup);
        }
      );
      this.getCages();
    }
  }

  editCage(cage) {
    this.setState({
      edit: true,
      cage: cage,
      name: cage.name,
      location: cage.location,
      openModal: true
    });
  }

  render() {
    const { classes } = this.props;

    return (
      <Fragment>
        <Typography variant="display1">Cages Manager</Typography>
        <ToastContainer />
        {this.state.cages.length > 0 ? (
          <Paper elevation={1} className={classes.cages}>
            <List>
              {orderBy(this.state.cages, ["id"]).map(cage => (
                <CageItem
                  key={cage.id}
                  editFunc={() => this.deleteCage(cage)}
                  deleteFunc={() => this.editCage(cage)}
                  cage={cage}
                />
              ))}
            </List>
          </Paper>
        ) : (
          !this.state.loading && <Typography variant="subheading">No cages to display</Typography>
        )}
        <Button
          onClick={this.handleClickOpen}
          variant="fab"
          color="secondary"
          aria-label="add"
          className={classes.fab}
        >
          <AddIcon />
        </Button>
        <Dialog
          open={this.state.openModal}
          onClose={this.handleClose}
          aria-labelledby="form-dialog-title"
        >
          <DialogTitle id="form-dialog-title">{this.state.edit ? "Edit" : "Add"} Cage</DialogTitle>
          <DialogContent>
            <DialogContentText>
              Please provide all the details for the cage creator.
            </DialogContentText>
            <TextField
              margin="dense"
              id="name"
              onChange={this.handleNameChange}
              label="Cage name"
              type="text"
              value={this.state.name}
              error={this.state.name.length === 0 ? true : false}
              helperText={this.state.fieldError}
              fullWidth
            />
            <TextField
              margin="dense"
              id="location"
              label="Cage Location"
              type="text"
              onChange={this.handleLocationChange}
              value={this.state.location}
              error={this.state.location.length === 0 ? true : false}
              helperText={this.state.fieldError}
              fullWidth
            />
          </DialogContent>
          <DialogActions>
            <Button onClick={this.handleClose} color="primary">
              Cancel
            </Button>
            <Button
              type="button"
              color="primary"
              onClick={() =>
                this.handleSubmit(this.state.edit, this.state.name, this.state.location)
              }
            >
              Save
            </Button>
          </DialogActions>
        </Dialog>
      </Fragment>
    );
  }
}

export default compose(
  withRouter,
  withStyles(styles)
)(CagesManager);
