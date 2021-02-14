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
    private identifiant: React.RefObject<any>;
    private role: React.RefObject<any>;

    state = {
        redirect: false,
    }

    constructor(props: IProps) {
        super(props);
        this.identifiant = createRef();
        this.role = createRef();


        this.getIdentifiant = this.getIdentifiant.bind(this);
        this.getRole = this.getRole.bind(this);
        this.addUser = this.addUser.bind(this);
    }

    getIdentifiant(): string {
        if (this.identifiant.current.value == null) {
            return "";
        } else {
            return this.identifiant.current.value;
        }
    };

    getRole(): string {
        if (this.role.current.value == null) {
            return "";
        } else {
            return this.role.current.value;
        }
    };

    addUser() {
        if (this.getIdentifiant() != "" && this.getRole() != "") {
            API.addUtilisateur(this.getIdentifiant(), this.getRole()).then(() => this.setState({redirect: true}));
        }
    }

    render() {
        return (
            <div>

                <Grid container direction="column" justify="center" alignItems="center" spacing={5}>
                    <h1>Nouvel utilisateur</h1>
                    <Grid item>
                        <TextField
                            inputRef={this.identifiant}
                            label="Identifiant:"
                            id="outlined-margin-normal"
                            margin="normal"
                            variant="outlined"
                        />
                    </Grid>
                    <Grid item>
                        <TextField
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