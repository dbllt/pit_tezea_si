import React, {Component, createRef} from 'react';
import {Button, TextField} from "@material-ui/core";
import {Link} from "react-router-dom";
import Grid from "@material-ui/core/Grid";
import API from "../../network/API";

import {Redirect} from 'react-router-dom';

interface IProps {
}

interface IState {
    redirect: boolean
}

class NewUserScreen extends Component<IProps, IState> {
    private username: React.RefObject<any>;
    private password: React.RefObject<any>;
    private role: React.RefObject<any>;

    state = {
        redirect: false,
    }

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
        if (this.getUsername() !== "" && this.getRole() !== ""&& this.getPassword() !== "") {
            API.addUser(this.getUsername(),this.getPassword(), this.getRole()).then(() => this.setState({redirect: true}));
        }
    }

    render() {
        return (
            <div>

                <Grid container direction="column" justify="center" alignItems="center" spacing={5}>
                    <h1>Nouvel utilisateur</h1>
                    <Grid item>
                        <TextField
                            inputRef={this.username}
                            label="Identifiant:"
                            id="outlined-margin-normal"
                            margin="normal"
                            variant="outlined"
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
                        />
                    </Grid>
                    <Grid item>
                        <TextField
                            inputRef={this.role}
                            label="Rôle"
                            id="outlined-margin-normal"
                            margin="normal"
                            variant="outlined"
                        />
                    </Grid>
                    <Grid>
                        <Button type="button" onClick={this.addUser}>
                            Ajouter utilisateur
                        </Button>
                    </Grid>
                    <Grid>
                        <Link to="/users">
                            <Button type="button">
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