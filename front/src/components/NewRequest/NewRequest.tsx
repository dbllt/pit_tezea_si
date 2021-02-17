import React, {Component, createRef} from "react";
import {Button, Container, TextField} from "@material-ui/core";
import Form from "@material-ui/core/FormControl";
import Grid from "@material-ui/core/Grid";
import FormControl from "@material-ui/core/FormControl";
import Select from "@material-ui/core/Select";
import MenuItem from "@material-ui/core/MenuItem";
import {Link, Redirect} from "react-router-dom";
import "./NewRequest.css";
import {RouteComponentProps} from "react-router-dom";
import FormClient from "../FormClient/FormClient";


type StateType = {
    service: string
}


interface IState {
    service: string,
    redirect: boolean
}

type IndexProps = RouteComponentProps<{}, {}, StateType>;

function RedirectionIfNotConnected() {
    let temp = localStorage.getItem('token');
    if (temp === null) {
        temp = "";
    }
    let token: string = temp;
    if (token==="") {
        return <Redirect to="/login"/>
    }else{
        return <div/>
    }
}
class NewRequest extends Component<IndexProps, IState> {
    private task: React.RefObject<any>;


    constructor(props: IndexProps) {
        super(props);
        this.state = {service: localStorage.getItem('service') || "", redirect: false};

        this.task = createRef();
        this.getTask = this.getTask.bind(this);
        this.addRequest = this.addRequest.bind(this);
    }

    getTask(): string {
        if (this.task.current == null) {
            return "";
        } else {
            return this.task.current.value;
        }
    };

    addRequest() {
        // if (this.getTask() !== "" || true) {//TODO remove true
        //     API.addRequest(this.getTask(), "").then(() => this.setState({redirect: true}));
        // }
        this.setState({redirect: true})
    }


    componentDidMount() {

        var res = this.props.location.state;
        if (res) {
            this.setState({service: res.service});
            localStorage.setItem('service', res.service);
        }
    }

    render() {
        return (
            <Container>
                <RedirectionIfNotConnected/>
                <h1>Service {this.state.service}</h1>
                <h1>Enregistrer une demande client</h1>
                <Form className="form">
                    <FormClient/>
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


                <Button className={"MyButton"} variant="contained" onClick={this.addRequest}>
                    Enregistrer la demande
                </Button>

                <Link to="/">
                    <Button className={"MyButton"} type="button">
                        Retour
                    </Button>
                </Link>
                {this.state.redirect ? (<Redirect push to="/"/>) : null}
            </Container>

        );
    }
}

export default NewRequest