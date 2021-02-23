import React, {Component, createRef} from 'react';
import {Button, InputLabel, Select, TextField} from "@material-ui/core";
import {Link, Redirect} from "react-router-dom";
import Grid from "@material-ui/core/Grid";
import API from "../../network/API";
import MenuItem from "@material-ui/core/MenuItem";
import FormControl from "@material-ui/core/FormControl";

interface IProps {
}

interface IState {
    redirect: boolean
    triedToCreate: boolean
}

function RedirectionIfNotConnected() {
    let temp = localStorage.getItem('token');
    if (temp === null) {
        temp = "";
    }
    let token: string = temp;
    if (token === "") {
        return <Redirect to="/login"/>
    } else {
        return <div/>
    }
}

class NewUserScreen extends Component<IProps, IState> {
    state = {
        redirect: false,
        triedToCreate: false,
    }
    private readonly username: React.RefObject<any>;
    private readonly password: React.RefObject<any>;
    private readonly role: React.RefObject<any>;

    constructor(props: IProps) {
        super(props);
        this.username = createRef();
        this.role = createRef();
        this.password = createRef();


        this.getUsername = this.getUsername.bind(this);
        this.getRole = this.getRole.bind(this);
        this.getPassword = this.getPassword.bind(this);
        this.addUser = this.addUser.bind(this);
    }

    getUsername(): string {
        if (this.username.current == null) {
            return "";
        } else {
            return this.username.current.value;
        }
    };

    getRole(): string {
        if (this.role.current.value == null) {
            return "";
        } else {
            return this.role.current.value;
        }
    };

    getPassword(): string {
        if (this.password.current.value == null) {
            return "";
        } else {
            return this.password.current.value;
        }
    };

    addUser() {
        this.setState({triedToCreate: true})
        if (this.getUsername() !== "" && this.getRole() !== "" && this.getPassword() !== "") {
            API.addUser(this.getUsername(), this.getPassword(), this.getRole()).then(() => this.setState({redirect: true}));
        }
    }

    render() {
        return (
            <div>
                <RedirectionIfNotConnected/>
                <h1>Nouvel utilisateur</h1>
                <Grid container direction="column" justify="center" alignItems="center" spacing={2}>
                    <Grid item>
                        <TextField
                            inputRef={this.username}
                            label="Identifiant"
                            id="outlined-margin-normal"
                            margin="normal"
                            variant="outlined"
                            error={(this.state.triedToCreate && this.getUsername() === "")}
                            helperText={(this.state.triedToCreate && this.getUsername() === "") ? 'Manquant' : ' '}
                        />
                    </Grid>
                    <Grid item>
                        <TextField
                            inputRef={this.password}
                            label="Mot de passe"
                            type="password"
                            id="outlined-margin-normal"
                            margin="normal"
                            variant="outlined"
                            error={(this.state.triedToCreate && this.getPassword() === "")}
                            helperText={(this.state.triedToCreate && this.getPassword() === "") ? 'Manquant' : ' '}
                        />
                    </Grid>
                    <Grid item>
                        <FormControl style={{
                            minWidth: 250,
                            marginTop:20

                        }}>
                            <InputLabel id="demo-simple-select-label" style={{marginLeft: 15}}>Rôle</InputLabel>
                            <Select name="typeRequest"
                                    inputRef={this.role}
                                    id="outlined-margin-normal"
                                    variant="outlined"
                                    error={(this.state.triedToCreate && this.getRole() === "")}
                            >
                                <MenuItem value="Responsable Site">Reponsable Site</MenuItem>
                                <MenuItem value="Concierge">Concierge</MenuItem>
                                <MenuItem value="Commercial">Commercial</MenuItem>
                                <MenuItem value="ADMIN">ADMIN</MenuItem>
                            </Select>
                        </FormControl>
                    </Grid>
                </Grid>
                    <Grid container direction="column" justify="center" alignItems="center" spacing={2} style={{marginTop:100}}>
                        <Grid item>
                            <Button type="button" onClick={this.addUser} style={{backgroundColor: "#8fbe40",color: 'white',padding:15}}>
                                Ajouter utilisateur
                            </Button>
                        </Grid>
                        <Grid item>
                            <Link to="/users">
                                <Button color="primary">
                                    Retour
                                </Button>
                            </Link>
                        </Grid>
                    </Grid>
                {this.state.redirect ? (<Redirect push to="/users"/>) : null}
            </div>
        );
    }
}

export default NewUserScreen
