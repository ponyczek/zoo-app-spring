import React, { Component, Fragment } from "react";
import { withRouter, Link } from "react-router-dom";
import {
  withStyles,
  Typography,
  Button,
  IconButton,
  Paper,
  List,
  ListItem,
  ListItemText,
  ListItemSecondaryAction,
  Dialog,
  TextField,
  DialogActions,
  DialogContent,
  DialogContentText,
  DialogTitle,
  Avatar,
  Select,
  MenuItem,
  InputLabel
} from "@material-ui/core";
import {
  Delete as DeleteIcon,
  Add as AddIcon,
  Edit as EditIcon,
  ArrowBack as BackIcon
} from "@material-ui/icons";
import { ToastContainer, toast } from "react-toastify";
import { orderBy } from "lodash";
import { compose } from "recompose";

const styles = theme => ({
  animals: {
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
  },
  backBtn: {
    position: "absolute",
    bottom: 3 * theme.spacing.unit,
    left: 3 * theme.spacing.unit,
    [theme.breakpoints.down("xs")]: {
      bottom: 2 * theme.spacing.unit,
      left: 2 * theme.spacing.unit
    }
  },
  bigAvatar: {
    width: 60,
    height: 60
  },
  scrollableList: {
    overflowY: "auto",
    maxHeight: 700
  }
});

const API = "http://localhost:8080/api/";

const toastSetup = {
  position: "top-right",
  autoClose: 4000,
  hideProgressBar: true,
  closeOnClick: true,
  pauseOnHover: true,
  draggable: true
};

class Cage extends Component {
  state = {
    loading: true,
    animals: [],
    openModal: false,
    name: "",
    age: "",
    animal: {},
    edit: false,
    cageId: "",
    url: "",
    animalClass: "",
    errors: { blank: "Field can't be blank." }
  };

  handleClickOpen = () => {
    this.setState({
      openModal: true,
      edit: false,
      animal: {},
      imgUrl: "",
      animalClass: "",
      name: "",
      age: ""
    });
  };

  handleClose = () => {
    this.setState({ openModal: false, name: "", animalClass: "" });
  };

  handlePutOrPost(name, age, animalClass, imgUrl, method, endpoint) {
    if (name && age && animalClass) {
      let id = this.state.animal.id;
      let data = JSON.stringify({ name, age, animalClass, imgUrl, id });
      let cageId = this.state.cageId;

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
            `The animal info has been ${method === "POST" ? "added" : "updated"} successfully.`,
            toastSetup
          );

          this.getAnimals(cageId);
          this.setState({ openModal: false });
        },
        err => {
          toast.error("Something went wrong", toastSetup);
        }
      );
    }
  }

  handleSubmit(edit, name, age, animalClass, imgUrl) {
    let method = edit ? "PUT" : "POST";
    let id = this.state.animal.id;
    let cageId = this.state.cageId;
    let endpoint = edit ? `cages/${cageId}/animals/${id}` : `cages/${cageId}/animals`;
    this.handlePutOrPost(name, age, animalClass, imgUrl, method, endpoint);
  }

  handleNameChange = e => {
    this.setState({ name: e.target.value });
  };

  handleAgeChange = e => {
    this.setState({ age: e.target.value });
  };

  handleImgUrlChange = e => {
    this.setState({ imgUrl: e.target.value });
  };

  componentDidMount() {
    let cageId = this.props.match.params.id;
    this.setState({ cageId: cageId });
    this.getAnimals(cageId);
  }

  handleAnimalClassChange = e => {
    this.setState({ animalClass: e.target.value });
  };

  async fetch(method, endpoint, body) {
    try {
      const response = await fetch(`${API}${endpoint}`, {
        method: method,
        body: body && JSON.stringify(body),
        headers: {
          "content-type": "application/json",
          accept: "application/json, text/plain, */*"
        }
      });
      return (await method) === "get" ? response.json() : response.status;
    } catch (error) {
      console.error(error);
    }
  }

  async getAnimals(cageId) {
    this.setState({ loading: false, animals: await this.fetch("get", `/cages/${cageId}/animals`) });
  }

  async deleteAnimal(animal) {
    let cageId = this.state.cageId;
    if (window.confirm(`Are you sure you want to delete ${animal.name}"`)) {
      await this.fetch("delete", `cages/${cageId}/animals/${animal.id}`).then(
        resp => {
          toast.success("The animal has been removed from the cage.", toastSetup);
        },
        err => {
          toast.error("Something went wrong", toastSetup);
        }
      );
      this.getAnimals(cageId);
    }
  }

  editAnimal(animal) {
    this.setState({
      edit: true,
      animal: animal,
      name: animal.name,
      age: animal.age,
      openModal: true,
      animalClass: animal.animalClass,
      imgUrl: animal.imgUrl
    });
  }

  render() {
    const { classes } = this.props;

    return (
      <Fragment>
        <Typography variant="display1">Animals in the Cage</Typography>
        <ToastContainer />
        {this.state.animals.length > 0 ? (
          <Paper elevation={1} className={classes.animals}>
            <List className={classes.scrollableList}>
              {orderBy(this.state.animals, ["name"]).map(animal => (
                <ListItem key={animal.id} button>
                  <Avatar
                    alt={animal.name}
                    src={animal.imgUrl ? animal.imgUrl : "../../placeholder.png"}
                    className={classes.bigAvatar}
                  />
                  <ListItemText
                    primary={animal.name}
                    secondary={
                      "Animal Classification: " + animal.animalClass + " | Age: " + animal.age
                    }
                  />
                  <ListItemSecondaryAction>
                    <IconButton onClick={() => this.deleteAnimal(animal)} color="inherit">
                      <DeleteIcon />
                    </IconButton>
                    <IconButton onClick={() => this.editAnimal(animal)} color="inherit">
                      <EditIcon />
                    </IconButton>
                  </ListItemSecondaryAction>
                </ListItem>
              ))}
            </List>
          </Paper>
        ) : (
          !this.state.loading && (
            <Typography variant="subheading">
              <img src="../../cage.png" alt="cage" />
              <br />
              Sorry! No Animals here.. Check other cages or add an Animal.
            </Typography>
          )
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
        <Button
          variant="fab"
          color="primary"
          aria-label="Add"
          component={Link}
          to="/cages"
          className={classes.backBtn}
        >
          <BackIcon />
        </Button>

        <Dialog
          open={this.state.openModal}
          onClose={this.handleClose}
          aria-labelledby="form-dialog-title"
        >
          <DialogTitle id="form-dialog-title">
            {this.state.edit ? "Edit" : "Add"} Animal
          </DialogTitle>
          <DialogContent>
            <DialogContentText>
              Please provide all the details for the Animal creator.
            </DialogContentText>
            <TextField
              margin="dense"
              id="name"
              onChange={this.handleNameChange}
              label="Animal name"
              type="text"
              value={this.state.name}
              error={!this.state.name.length}
              helperText={this.state.errors.blank}
              fullWidth
            />
            <TextField
              margin="dense"
              id="age"
              label="Age of the animal"
              type="number"
              onChange={this.handleAgeChange}
              value={this.state.age}
              error={!this.state.age}
              helperText={this.state.errors.blank}
              fullWidth
            />
            <InputLabel htmlFor="age-simple">Animal Classification:</InputLabel>
            <br />
            <Select
              error={!this.state.animalClass}
              value={this.state.animalClass}
              onChange={this.handleAnimalClassChange}
            >
              <MenuItem value={"AMPHIBIAN"}>AMPHIBIAN</MenuItem>
              <MenuItem value={"ARTHROPOD"}>ARTHROPOD</MenuItem>
              <MenuItem value={"BIRD"}>BIRD</MenuItem>
              <MenuItem value={"MAMMAL"}>MAMMAL</MenuItem>
              <MenuItem value={"REPTILE"}>REPTILE</MenuItem>
            </Select>
            <TextField
              margin="dense"
              id="age"
              label="Url to the image of the animal"
              type="text"
              onChange={this.handleImgUrlChange}
              value={this.state.imgUrl}
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
                this.handleSubmit(
                  this.state.edit,
                  this.state.name,
                  this.state.age,
                  this.state.animalClass,
                  this.state.imgUrl
                )
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
)(Cage);
