import React, {Component, createRef} from "react";
import {Button, Container, TextField} from "@material-ui/core";
import Form from "@material-ui/core/FormControl";
import Grid from "@material-ui/core/Grid";
import MenuItem from "@material-ui/core/MenuItem";
import {Link, Redirect, RouteComponentProps} from "react-router-dom";
import "./Request.css";
import FormClient from "../FormClient/FormClient";
import API from "../../network/API";

interface IRequest {
    id: string;
    date: string,
    hour: string,
    concierge: string,
    site: string,
    serviceType: string,
    requestStatus: string,
    requestAssignment: string,
    executionDate: Date,
    clientStatus: string,
    company: string,
    gender: string,
    lName: string,
    fName: string,
    phone: string,
    email: string,
    address: string,
    cp: string,
    city: string
}

type StateType = {
    requestId: string;
    service: string
}


interface IState {
    service: string,
    redirect: boolean,
    request: IRequest,
    images: File [];

}

type IndexProps = RouteComponentProps<{}, {}, StateType>;


const regularity = [
    {
        value: 'day',
        label: 'jour',
    },
    {
        value: 'week',
        label: 'semaine',
    },
    {
        value: 'month',
        label: 'mois',
    },
    {
        value: 'year',
        label: 'année',
    },
];


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

class Request extends Component<IndexProps, IState> {
    private task: React.RefObject<any>;


    constructor(props: IndexProps) {
        super(props);
        this.state = {
            service: localStorage.getItem('service') || "",
            redirect: false,
            images: [],
            request: {
                address: "",
                city: "",
                clientStatus: "",
                company: "",
                concierge: "",
                cp: "",
                date: "",
                email: "",
                executionDate: new Date(1, 1, 1),
                fName: "",
                gender: "",
                hour: "",
                id: "",
                lName: "",
                phone: "",
                requestAssignment: "",
                requestStatus: "",
                serviceType: "",
                site: ""
            }
        }


        this.task = createRef();
        this.getTask = this.getTask.bind(this);
        this.addRequest = this.addRequest.bind(this);
        this.addImage = this.addImage.bind(this); //upload images
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


    loadRequest(id: string) {
        API.getRequest(id).then(r => {
            if (r !== null) {
                var rr: IRequest = r;
                const request: IRequest = {
                    address: rr.address,
                    city: rr.city,
                    clientStatus: rr.clientStatus,
                    company: rr.company,
                    concierge: rr.concierge,
                    cp: rr.cp,
                    date: rr.date,
                    email: rr.email,
                    executionDate: rr.executionDate,
                    fName: rr.fName,
                    gender: rr.gender,
                    hour: rr.hour,
                    id: rr.id,
                    lName: rr.lName,
                    phone: rr.phone,
                    requestAssignment: rr.requestAssignment,
                    requestStatus: rr.requestStatus,
                    serviceType: rr.serviceType,
                    site: rr.site

                }

                this.setState({
                    request: request
                });
            } else {
                const request: IRequest = {
                    address: "",
                    city: "",
                    clientStatus: "",
                    company: "",
                    concierge: "",
                    cp: "",
                    date: "",
                    email: "",
                    executionDate: new Date(1, 1, 1),
                    fName: "",
                    gender: "",
                    hour: "",
                    id: "",
                    lName: "",
                    phone: "",
                    requestAssignment: "",
                    requestStatus: "",
                    serviceType: "",
                    site: ""
                }

                this.setState({
                    request: request
                });
            }
        })
    }

    componentDidMount() {
        const res = this.props.location.state;
        if (res) {
            this.setState({service: res.service});
            localStorage.setItem('service', res.service);
            localStorage.setItem('requestId', res.requestId);
            this.loadRequest(res.requestId);

        }
        let requestId = localStorage.getItem('requestId') || "";

        if (!res && requestId !== "") {
            this.loadRequest(requestId);
        }
    }

    addImage(e: any) {
        if (this.state.images.length < 3) {
            this.state.images.push(e.target.files[0])
            this.setState({images: this.state.images});
        }
    }; // upload images

    render() {
        return (
            <Container>
                <RedirectionIfNotConnected/>
                <h1>Service {this.state.service}</h1>
                <h1>Enregistrer une demande client</h1>
                <FormClient/>
                <Form className="form">
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
                                <TextField
                                    id="standard-select-currency"
                                    select
                                    value={'day'}
                                >
                                    {regularity.map((option) => (
                                        <MenuItem key={option.value} value={option.value}>
                                            {option.label}
                                        </MenuItem>
                                    ))}
                                </TextField>
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

                    <Grid container direction="row" justify="flex-start" alignItems="flex-start" spacing={1}>
                        <h4 className="h4">Concierge</h4>
                        <Grid container justify={"space-evenly"}>
                            <Grid item>
                                <TextField
                                    value={this.state.request.concierge}
                                    id="outlined-multiline-static"
                                    variant="outlined"
                                />
                            </Grid>
                        </Grid>
                    </Grid>
                    <Grid container direction="row" justify="flex-start" alignItems="flex-start" spacing={3}>
                        <h4 className="h4">Joindre des images</h4>
                        <Grid container justify={"space-evenly"} spacing={2}>
                            <Grid item>
                                <input type="file" accept="image/*" id="contained-button-file"
                                       onChange={this.addImage} multiple style={{display: 'none'}}
                                />
                                <label htmlFor="contained-button-file">
                                    <Button variant="outlined" color="primary" component="span">
                                        Ajouter une image
                                    </Button>
                                </label>
                            </Grid>
                            <Grid container spacing={3} justify="center">
                                {this.state.images.map((image, index) => (
                                    <Grid item>
                                        <Grid container direction="column">
                                            <Grid item>
                                                <img src={URL.createObjectURL(image)} alt="" width="150" height="100"/>
                                            </Grid>
                                            <Grid item>
                                                <Button variant="outlined" onClick={() => {
                                                    this.state.images.splice(index, 1)
                                                    this.setState({images: this.state.images});
                                                }}>Supprimer</Button>
                                            </Grid>
                                        </Grid>
                                    </Grid>
                                ))}
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

export default Request
