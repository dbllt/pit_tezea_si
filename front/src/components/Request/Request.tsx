import React, {Component, createRef} from "react";
import {Button, Checkbox, Container, FormControlLabel, FormGroup, Select, TextField} from "@material-ui/core";
import Form from "@material-ui/core/FormControl";
import Grid from "@material-ui/core/Grid";
import MenuItem from "@material-ui/core/MenuItem";
import {Link, Redirect, RouteComponentProps} from "react-router-dom";
import "./Request.css";
import FormClient from "../FormClient/FormClient";
import API from "../../network/API";
import { KeyboardDatePicker } from "@material-ui/pickers";

interface IRequest {
    id: string;
    date: string,
    hour: string,
    concierge: string,
    site: string,
    numberPerson: string,
    place: string,
    requestDesc : string,
    typeRequest: string,
    requestStatus: string,
    requestAssignment: string,
    executionDate: Date,
    client : IClient
}

interface IClient {
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
    id: string;
    date: string,
    hour: string,
    concierge: string,
    site: string,
    typeRequest: string,
    requestDesc : string,
    numberPerson: string,
    place: string,
    regularity: string,
    duration: string,
    material: string,
    internalInfo: string,
    executionDate: Date,
    requestStatus: string,
    requestAssignment: string,
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
            id: "",
            date: "",
            hour: "",
            concierge: "",
            site: "",
            typeRequest: "",
            requestDesc : "",
            numberPerson: "",
            place: "",
            regularity: "", 
            duration: "",
            material: "",   
            internalInfo:"", 
            executionDate: new Date(),       
            requestStatus: "",
            requestAssignment: "",
            images: []
        }
        let requestId = localStorage.getItem('requestId') || "";
        if (requestId !== "") {
           // this.loadRequest(requestId);
        }
        this.task = createRef();
        this.getTask = this.getTask.bind(this);
        this.addImage = this.addImage.bind(this); //upload images
        this.handleChange = this.handleChange.bind(this);
        this.addRequest = this.addRequest.bind(this);

    }

    getTask(): string {
        if (this.task.current == null) {
            return "";
        } else {
            return this.task.current.value;
        }
    };

    // loadRequest(id: string) {
    //     API.getRequest(id).then(r => {
    //         console.log(r)
    //         if (r !== null) {
    //             var rr: IRequest = r;
    //             const request: IRequest = {
    //                 address: rr.address,
    //                 city: rr.city,
    //                 clientStatus: rr.clientStatus,
    //                 company: rr.company,
    //                 concierge: rr.concierge,
    //                 cp: rr.cp,
    //                 date: rr.date,
    //                 email: rr.email,
    //                 executionDate: rr.executionDate,
    //                 fName: rr.fName,
    //                 gender: rr.gender,
    //                 hour: rr.hour,
    //                 id: rr.id,
    //                 lName: rr.lName,
    //                 phone: rr.phone,
    //                 requestAssignment: rr.requestAssignment,
    //                 requestStatus: rr.requestStatus,
    //                 serviceType: rr.serviceType,
    //                 site: rr.site,
    //                 requestDesc : rr.requestDesc

    //             }

    //             this.setState({
    //                 request: request
    //             });
    //         } else {
    //             const request: IRequest = {
    //                 address: "",
    //                 city: "",
    //                 clientStatus: "",
    //                 company: "",
    //                 concierge: "",
    //                 cp: "",
    //                 date: "",
    //                 email: "",
    //                 executionDate: new Date(1, 1, 1),
    //                 fName: "",
    //                 gender: "",
    //                 hour: "",
    //                 id: "",
    //                 lName: "",
    //                 phone: "",
    //                 requestAssignment: "",
    //                 requestStatus: "",
    //                 serviceType: "",
    //                 site: "",
    //                 requestDesc : ""
    //             }

    //             this.setState({
    //                 request: request
    //             });
    //         }
    //     })
    // }

    componentDidMount() {
        const res = this.props.location.state;
        if (res) {
            this.setState({service: res.service});
            localStorage.setItem('service', res.service);
            localStorage.setItem('requestId', res.requestId);
            //this.loadRequest(res.requestId);
        }
        let requestId = localStorage.getItem('requestId') || "";

        if (!res && requestId !== "") {
            //this.loadRequest(res.requestId);
        }
    }

    addImage(e: any) {
        if (this.state.images.length < 3) {
            this.state.images.push(e.target.files[0])
            this.setState({images: this.state.images});
        }
    }; // upload images

    materialChecked(event:any){
    }

    handleChange(event:any){
        // const {target: {name, value}} = event;
        // const newState = {request : {[name]: value}} as Pick<IState, keyof IState>;
        // this.setState(newState);
        const {target: {name, value}} = event;
        const newState = {[name]: value} as Pick<IState, keyof IState>;
        this.setState(newState);
    }

    addRequest(event:any) {
        // if (this.getTask() !== "" || true) {//TODO remove true
        //     API.addRequest(this.getTask(), "").then(() => this.setState({redirect: true}));
        // }
        alert(this.state.typeRequest)
        //this.setState({redirect: true})
    }

    render() {
        return (
            <Container>
                <RedirectionIfNotConnected/>
                <h1>Service {this.state.service}</h1>
                <h1>Enregistrer une demande client</h1>
                
                <Form className="form">  
                <Grid container direction="column" alignItems="flex-start" justify="flex-start">
                    <Grid item>
                     <FormClient/>
                    </Grid> 
                 
                    <Grid item>
                        <h3>Demande client : </h3>
                    </Grid>
                    <Grid item>
                        <Grid container className="Gridlabelfield">
                            <Grid item className="Label">
                                Type de demande :
                            </Grid>
                            <Grid item>
                                <Select name="typeRequest"
                                        value={this.state.typeRequest} onChange={this.handleChange} required>
                                    <MenuItem value="Prestation">Prestation</MenuItem>
                                    <MenuItem value="Don">Don</MenuItem>
                                    <MenuItem value="Enlèvement et don">Enlèvement et don</MenuItem>
                                    <MenuItem value="Renseignement">Renseignement</MenuItem>
                                </Select>
                            </Grid>
                        </Grid>
                        <Grid container className="Gridlabelfield">
                            <Grid item className="Label">
                                Demande client :
                            </Grid>
                            <Grid item>
                                <TextField
                                    className="text"                                   
                                    variant="outlined"
                                    size="small"
                                    name="requestDesc"
                                    value={this.state.requestDesc}
                                    onChange={this.handleChange}
                                />
                            </Grid>
                        </Grid>                        
                        <Grid container className="Gridlabelfield">
                            <Grid item className="Label">
                                Nombre de personnes :
                            </Grid>
                            <Grid item>
                                <TextField type="number"
                                    variant="outlined"
                                    className="number"
                                    size="small"
                                    name="numberPerson"
                                    value={this.state.numberPerson}
                                    onChange={this.handleChange} />
                            </Grid>
                        </Grid>
                        <Grid container className="Gridlabelfield">
                            <Grid item className="Label">
                                Accès au lieu :
                            </Grid>
                            <Grid item>
                                <TextField
                                    className="text"                                   
                                    variant="outlined"
                                    size="small"
                                    name="place"
                                    value={this.state.place}
                                    onChange={this.handleChange}
                                />
                            </Grid>
                        </Grid>
                        <Grid container className="Gridlabelfield">
                            <Grid item className="Label">
                                Regularité :
                            </Grid>
                            <Grid item>
                                <TextField type="number"
                                    variant="outlined"
                                    className="number"
                                    size="small"
                                    name="regularity"
                                    value={this.state.regularity}
                                    onChange={this.handleChange} />
                            </Grid>
                        </Grid>
                        <Grid container className="Gridlabelfield">
                            <Grid item className="Label">
                                Estimation du temps (heure) :
                            </Grid>
                            <Grid item>
                                <TextField
                                    type="number"
                                    inputProps={{ step: "0.5" }} 
                                    className="number"                                   
                                    variant="outlined"
                                    size="small"
                                    name="duration"
                                    value={this.state.duration}
                                    onChange={this.handleChange}
                                /> 
                            </Grid>
                        </Grid>
                        <Grid container className="Gridlabelfield">
                            <Grid item className="Label">
                                Matériel attendu :
                            </Grid>
                            <Grid item>
                                <FormGroup>
                                    <FormControlLabel
                                        control={<Checkbox value="Matériel lié au port de charge" onChange={this.materialChecked}/>}
                                        label="Matériel lié au port de charge"
                                    />
                                    <FormControlLabel
                                        control={<Checkbox value="Matériel lié à la prestation" onChange={this.materialChecked}/>}
                                        label="Matériel lié à la prestation"
                                    />
                                    <FormControlLabel
                                        control={<Checkbox value="Autre" onChange={this.materialChecked} />}
                                        label="Autre"
                                    />
                                   { <TextField value={this.state.material}></TextField> }                                     
                                </FormGroup>
                            </Grid>
                        </Grid>
                        <Grid container className="Gridlabelfield">
                            <Grid item className="Label">
                                Particularité :
                            </Grid>
                            <Grid item>
                                <FormGroup>
                                    <FormControlLabel
                                        control={<Checkbox value="??" onChange={this.materialChecked}/>}
                                        label="??"
                                    />                               
                                </FormGroup>
                            </Grid>
                        </Grid>
                        <Grid container className="Gridlabelfield">
                            <Grid item className="Label">
                                Informations internes :
                            </Grid>
                            <Grid item>
                                <TextField
                                    className="text"                                   
                                    variant="outlined"
                                    size="small"
                                    name="internalInfo"
                                    value={this.state.internalInfo}
                                    onChange={this.handleChange}
                                />
                            </Grid>
                        </Grid>
                        <Grid container className="Gridlabelfield">
                            <Grid item className="Label">
                                Date d’exécution :
                            </Grid>
                            <Grid item>
                                <TextField
                                    id="date"
                                    type="date"
                                    value={this.state.executionDate}
                                    onChange={this.handleChange}
                                    InputLabelProps={{
                                    shrink: true,
                                    }}
                                />
                            </Grid>
                        </Grid> 
                        <Grid container className="Gridlabelfield">
                            <Grid item className="Label">
                                Horaire d’exécution :
                            </Grid>
                            <Grid item>
                                <TextField
                                    type="time"
                                    onChange={this.handleChange}
                               />
                            </Grid>
                        </Grid> 
                        <Grid container className="Gridlabelfield">
                            <Grid item className="Label">
                                Joindre des images
                            </Grid>
                            <Grid item>
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
                        </Grid> 
                    </Grid>
                 
                </Grid>
                                                             
                <Button className={"MyButton"} variant="contained" onClick={this.addRequest}>
                    Enregistrer la demande
                </Button>

                <Link to="/">
                    <Button className={"MyButton"} type="button">
                        Retour
                    </Button>
                </Link>
                {this.state.redirect ? (<Redirect push to="/"/>) : null}
              </Form>
            </Container>

        );
    }
}

export default Request
