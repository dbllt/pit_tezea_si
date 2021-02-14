import React, {Component} from "react";
import {Button, Container, TextField} from "@material-ui/core";
import Form from "@material-ui/core/FormControl";
import Grid from "@material-ui/core/Grid";
import FormControl from "@material-ui/core/FormControl";
import Select from "@material-ui/core/Select";
import MenuItem from "@material-ui/core/MenuItem";
import {Link} from "react-router-dom";
import "./NewRequest.css";
import {RouteComponentProps} from "react-router-dom";
import FormClient from "../FormClient/FormClient";


type StateType = {
    service: string
}

interface IState {
    service: string
}

type IndexProps = RouteComponentProps<{}, {}, StateType>;

class NewRequest extends Component<IndexProps, IState> {

    constructor(props: IndexProps) {
        super(props);
        this.state = {service:""};
    }
    componentDidMount() {

        var res = this.props.location.state;
        if(res)
            this.setState({service: res.service});
    }

    render() {
        return (
            <Container>
                <h1>Service {this.state.service}</h1>
                <h1>Enregistrer une demande client</h1>
                <Form className="form">
                    <FormClient></FormClient>
                    <Grid container direction="row" justify="flex-start" alignItems="flex-start" spacing={1}>
                        <h4 className="h4">Demande client</h4>
                        <Grid container justify={"space-evenly"}>
                            <Grid item>
                                <TextField
                                    id="outlined-multiline-static"
                                    multiline
                                    fullWidth={true}
                                    rows={4}
                                    variant="outlined"
                                />
                            </Grid>
                        </Grid>
                    </Grid>
                    <Grid container direction="row" justify="flex-start" alignItems="flex-start" spacing={1}>
                        <h4 className="h4">Matériel attendu</h4>
                        <Grid container justify={"space-evenly"}>
                            <Grid item>
                                <TextField
                                    id="outlined-margin-normal"
                                    className="textField"
                                    margin="normal"
                                    variant="outlined"
                                />
                            </Grid>
                        </Grid>
                    </Grid>
                    <Grid container direction="row" justify="flex-start" alignItems="flex-start" spacing={1}>
                        <h4 className="h4">Estimation du temps</h4>
                        <Grid container
                              alignItems="center"
                              justify="center"
                              spacing={2}>
                            <Grid item>
                                <TextField
                                    id="outlined-margin-normal"
                                    className="textField"
                                    margin="normal"
                                    variant="outlined"
                                    type="number"
                                />
                            </Grid>
                            <Grid item>
                                <p>jours</p>
                            </Grid>
                            <Grid item>
                                <TextField
                                    id="outlined-margin-normal"
                                    className="textField"
                                    margin="normal"
                                    variant="outlined"
                                    type="number"
                                />
                            </Grid>
                            <Grid item>
                                <p>heures</p>
                            </Grid>
                            <Grid item>
                                <TextField
                                    id="outlined-margin-normal"
                                    className="textField"
                                    margin="normal"
                                    variant="outlined"
                                    type="number"
                                />
                            </Grid>
                            <Grid item>
                                <p>minutes</p>
                            </Grid>
                        </Grid>
                    </Grid>
                    <Grid container direction="row" justify="flex-start" alignItems="flex-start" spacing={1}>
                        <h4 className="h4">Régularité</h4>
                        <Grid container
                              alignItems="center"
                              justify="center"
                              spacing={2}>
                            <Grid item>
                                <TextField
                                    id="outlined-margin-normal"
                                    className="textField"
                                    margin="normal"
                                    variant="outlined"
                                    type="number"
                                />
                            </Grid>
                            <Grid item>
                                <p>par</p>
                            </Grid>
                            <Grid item>
                                <FormControl variant="outlined" className="formControl">
                                    <Select
                                        labelId="demo-simple-select-outlined-label"
                                        id="demo-simple-select-outlined"
                                    >
                                        <MenuItem value="day">jour</MenuItem>
                                        <MenuItem value="week">semaine</MenuItem>
                                        <MenuItem value="month">mois</MenuItem>
                                        <MenuItem value="year">année</MenuItem>
                                    </Select>
                                </FormControl>
                            </Grid>
                        </Grid>
                    </Grid>
                    <Grid container direction="row" justify="flex-start" alignItems="flex-start" spacing={1}>
                        <h4 className="h4">Particularité</h4>
                        <Grid container justify={"space-evenly"}>
                            <Grid item>
                                <TextField
                                    id="outlined-multiline-static"
                                    multiline
                                    fullWidth={true}
                                    rows={4}
                                    variant="outlined"
                                />
                            </Grid>
                        </Grid>
                    </Grid>
                    <Grid container direction="row" justify="flex-start" alignItems="flex-start" spacing={1}>
                        <h4 className="h4">Informations internes</h4>
                        <Grid container justify={"space-evenly"}>
                            <Grid item>
                                <TextField
                                    id="outlined-multiline-static"
                                    multiline
                                    fullWidth={true}
                                    rows={4}
                                    variant="outlined"
                                />
                            </Grid>
                        </Grid>
                    </Grid>
                </Form>


                <Button className={"truc"} variant="contained" type="submit">
                    Enregistrer la demande
                </Button>

                <Link to="/menu">
                    <Button className={"truc"} type="button">
                        Retour
                    </Button>
                </Link>
            </Container>

        );
    }
}

export default NewRequest