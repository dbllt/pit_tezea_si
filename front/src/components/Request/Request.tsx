import React, {Component, createRef} from "react";
import {Button, Checkbox, Container, FormControlLabel, FormGroup, Select, TextField} from "@material-ui/core";
import Form from "@material-ui/core/FormControl";
import Grid from "@material-ui/core/Grid";
import MenuItem from "@material-ui/core/MenuItem";
import {Link, Redirect, RouteComponentProps} from "react-router-dom";
import "./Request.css";
import FormClient from "../FormClient/FormClient";
import API, {PatchClient, PatchRequest} from "../../network/API";

interface IRequest {
    id: string,
    concierge: string,
    site: string,
    typeRequest: string,
    requestDesc: string,
    numberPerson: string,
    place: string,
    regularity: string,
    duration: string,
    material: string [],
    materialother: string,
    internalInfo: string,
    executionDate: string,
    executionTime: string,
    requestStatus: string,
    requestAssignment: string,
    images: File [],
    photos: string[],
    client: IClient,
    wood:string,
}

interface IClient {
    clientStatus: string,
    company: string,
    siret: string,
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

    displayError: boolean,
    triedToAdd: boolean
    service: string,
    redirect: boolean,
    id: string,
    concierge: string,
    site: string,
    typeRequest: string,
    requestDesc: string,
    numberPerson: string,
    place: string,
    regularity: string,
    duration: string,
    material: string [],
    materialother: string,
    internalInfo: string,
    executionDate: string,
    executionTime: string,
    requestStatus: string,
    requestAssignment: string,
    images: File [],
    photos: string[],
    client: IClient,
    nameEstimator: string,
    typeTruck: boolean[],
    quantityWood: string

}

type IndexProps = RouteComponentProps<{}, {}, StateType>;


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
            triedToAdd: false,
            displayError: false,
            service: localStorage.getItem('service') || "",
            redirect: false,
            id: "",
            concierge: "",
            site: "",
            typeRequest: "Prestation",
            requestDesc: "",
            numberPerson: "",
            place: "",
            regularity: "",
            duration: "",
            material: [],
            materialother: "",
            internalInfo: "",
            executionDate: this.dateNow(),
            executionTime: "",
            requestStatus: "",
            requestAssignment: "",
            images: [],
            photos: [],
            nameEstimator: "",
            typeTruck: [false, false, false],
            quantityWood: "",
            client: {
                clientStatus: "Particulier",
                company: "",
                siret: "",
                gender: "Mr",
                lName: "",
                fName: "",
                phone: "",
                email: "",
                address: "",
                cp: "",
                city: ""
            }
        }
        this.task = createRef();
        this.getTask = this.getTask.bind(this);
        this.addImage = this.addImage.bind(this); //upload images
        this.materialChecked = this.materialChecked.bind(this);
        this.typeTruckChecked = this.typeTruckChecked.bind(this);
        this.callbackFunction = this.callbackFunction.bind(this);
        this.handleChange = this.handleChange.bind(this);
        this.addRequest = this.addRequest.bind(this);
        this.DisplayError = this.DisplayError.bind(this);


    }

    DisplayError() {
        if (this.state.displayError) {
            return <p style={{color: "red"}}>Erreur : Champs invalides</p>
        } else {
            return <div/>
        }
    }

    dateNow() {
        let d = new Date();
        let m: any;
        let day: any;
        if (d.getMonth() < 9) m = "0" + (d.getMonth() + 1);
        else m = d.getMonth();

        if (d.getDate() < 9) day = "0" + d.getDate();
        else day = d.getDate();

        return d.getFullYear() + "-" + m + "-" + day;
    }

    getTask(): string {
        if (this.task.current == null) {
            return "";
        } else {
            return this.task.current.value;
        }
    };

    truc(date: string) {
        var t = date.split("-");
        var ret = t[2] + "-" + t[1] + "-" + t[0]
        return ret;
    }

    loadRequest(id: string) {
        API.getRequest(id).then(r => {
            if (r !== null) {
                var rr: IRequest = r;


                this.setState({
                    id: rr.id,
                    concierge: rr.concierge,
                    site: rr.site,
                    typeRequest: rr.typeRequest,
                    requestDesc: rr.requestDesc,
                    numberPerson: rr.numberPerson,
                    place: rr.place,
                    regularity: rr.regularity,
                    duration: rr.duration,
                    material: rr.material,
                    materialother:rr.materialother,
                    internalInfo: rr.internalInfo,
                    executionDate: this.truc(rr.executionDate),
                    requestStatus: rr.requestStatus,
                    requestAssignment: rr.requestAssignment,
                    images: rr.images,
                    photos: rr.photos,
                    quantityWood:rr.wood,
                    client: {
                        clientStatus: rr.client.clientStatus,
                        company: rr.client.company,
                        siret: rr.client.siret,
                        gender: rr.client.gender,
                        lName: rr.client.lName,
                        fName: rr.client.fName,
                        phone: rr.client.phone,
                        email: rr.client.email,
                        address: rr.client.address,
                        cp: rr.client.cp,
                        city: rr.client.city,

                    }
                });

                API.photosAddressesToFiles(rr.photos).then((files: File[]) => {
                    this.setState({images: files})
                });
            } else {
                this.setState({
                    id: "",
                    concierge: "",
                    site: "",
                    typeRequest: "Prestation",
                    requestDesc: "",
                    numberPerson: "",
                    place: "",
                    regularity: "",
                    duration: "",
                    material: [],
                    materialother: "",
                    internalInfo: "",
                    executionDate: this.dateNow(),
                    executionTime: "",
                    requestStatus: "",
                    requestAssignment: "",
                    images: []
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
        this.forceUpdate()
    }

    addImage(e: any) {
        if (this.state.images.length < 3) {
            this.state.images.push(e.target.files[0])
            this.setState({images: this.state.images});
        }
    }; // upload images

    removeMaterial(materials:string[],name: string):string[]{
        let index = materials.findIndex((value) => value === name);
        if (index > -1) {
            materials.splice(index, 1);
        }
        return materials;
    };


    materialChecked(event: any) {
        let data: string[] = this.state.material;
        let material: string;
        if (event.target.name === "m1")
            material = "Port de charge";
        else if (event.target.name === "m2")
            material = "Lié à la prestation";
        else
            material = "Spécifique";

        if (event.target.checked) {
            data.push(material) ;
            this.setState({material: data})
        } else {
            data=this.removeMaterial(data,material)
            this.setState({material: data})
        }
    }

    typeTruckChecked(event: any) {
        let data: boolean[] = this.state.typeTruck;
        let indice: number;
        if (event.target.name === "t1")
            indice = 0;
        else if (event.target.name === "t2")
            indice = 1;
        else
            indice = 2;

        if (event.target.checked) {
            data[indice] = true;
            this.setState({typeTruck: data})
        } else {
            data[indice] = false;
            this.setState({typeTruck: data})
        }
    }

    callbackFunction(childData: IClient) {
        this.setState({client: childData})
    }

    handleChange(event: any) {
        const {target: {name, value}} = event;
        const newState = {[name]: value} as Pick<IState, keyof IState>;
        this.setState(newState);
    }

    addRequest() {

        this.setState({triedToAdd: true})
        var client = this.state.client;

        var request: IRequest = {
            id: "-1",
            site: this.state.service,
            concierge: this.state.concierge,
            typeRequest: this.state.typeRequest,
            requestDesc: this.state.requestDesc,
            numberPerson: this.state.numberPerson,
            place: this.state.place,
            regularity: this.state.regularity,
            duration: this.state.duration,
            material: this.state.material,
            materialother: this.state.materialother,
            internalInfo: this.state.internalInfo,
            executionDate: this.state.executionDate,
            executionTime: this.state.executionTime,
            images: this.state.images,
            requestStatus: "",
            requestAssignment: "",
            client: client,
            photos: [],
            wood:this.state.quantityWood
        }


        if (this.state.id === "") {
            API.addRequest(request).then((b) => {
                    if (b) {
                        this.setState({redirect: true})
                    } else {
                        this.setState({displayError: true})
                    }
                }
            );
        } else {

            var t = this.state.executionDate.split("-");
            var date = t[2] + "-" + t[1] + "-" + t[0]

            var patchClient: PatchClient = {
                email: client.email,
                phoneNumber: client.phone,
                address: client.address,
                postCode: client.cp,
                city: client.city,
                companyName: client.company,
                lastName: client.lName,
                firstName: client.fName,
                honorificTitle: client.gender,
                type: client.clientStatus,
                siret:client.siret

            }

            var patchRequest: PatchRequest = {
                id: +this.state.id,
                date: date,
                site: this.state.service,
                responsable: {username: this.state.concierge},
                client: patchClient,
                description: this.state.requestDesc,
                status: this.state.requestStatus,
                accessDetails: this.state.place,
                repetitionTime: +this.state.regularity,
                type: this.state.typeRequest,
                amountWood: +this.state.quantityWood,
                estimation: {
                    numberEmployeesNeeded: +this.state.numberPerson,
                    expectedDuration: +this.state.duration,
                    toolsNeeded:this.state.material,
                    otherTools:this.state.materialother
                },
                internalInfo: this.state.internalInfo
            }
            API.editRequest(patchRequest).then((b) => {
                    if (b) {
                        this.setState({redirect: true})
                    } else {
                        this.setState({displayError: true})
                    }
                }
            );
        }

    }

    containsMaterial(materials:string[],name: string):boolean{
        return materials.findIndex((value) => value === name)>-1;
    };

    render() {
        return (
            <Container fixed>
                <RedirectionIfNotConnected/>
                <h1>Service {this.state.service}</h1>
                <h1>Enregistrer une demande client</h1>
                <this.DisplayError/>

                <Form className="form">
                    <Grid container direction="column" alignItems="flex-start" justify="center">
                        <Grid item>
                            <FormClient parentCallback={this.callbackFunction} client={this.state.client}/>
                        </Grid>
                        <Grid item>
                            <h3>Demande client : </h3>
                        </Grid>
                        <Grid item>
                            <Grid container className="Gridlabelfield">
                                <Grid item className="Label">
                                    Concierge :
                                </Grid>
                                <Grid item>
                                    <TextField
                                        variant="outlined"
                                        size="small"
                                        name="concierge"
                                        value={this.state.concierge}
                                        onChange={this.handleChange}/>
                                </Grid>
                            </Grid>
                            {this.state.service === "Estimateur" &&
                            <Grid container className="Gridlabelfield">
                                <Grid item className="Label">
                                    Nom de l'estimateur :
                                </Grid>
                                <Grid item>
                                    <TextField
                                        variant="outlined"
                                        size="small"
                                        name="nameEstimator"
                                        value={this.state.nameEstimator}
                                        onChange={this.handleChange}/>
                                </Grid>
                            </Grid>}
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
                                               onChange={this.handleChange}/>
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
                                               onChange={this.handleChange}/>
                                </Grid>
                            </Grid>
                            <Grid container className="Gridlabelfield">
                                <Grid item className="Label">
                                    Estimation du temps (heure) :
                                </Grid>
                                <Grid item>
                                    <TextField
                                        type="number"
                                        inputProps={{step: "0.5"}}
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
                                            control={<Checkbox name="m1" checked={this.containsMaterial(this.state.material,"Port de charge")}
                                                               onChange={this.materialChecked}/>}
                                            label="Matériel lié au port de charge"
                                        />
                                        <FormControlLabel
                                            control={<Checkbox name="m2" checked={this.containsMaterial(this.state.material,"Lié à la prestation")}
                                                               onChange={this.materialChecked}/>}
                                            label="Matériel lié à la prestation"
                                        />
                                        <FormControlLabel
                                            control={<Checkbox name="m3" checked={this.containsMaterial(this.state.material,"Spécifique")}
                                                               onChange={this.materialChecked}/>}
                                            label="Spécifique"
                                        />
                                        {this.containsMaterial(this.state.material,"Spécifique") &&
                                        <TextField name="materialother" value={this.state.materialother}
                                                   onChange={this.handleChange}/>}
                                    </FormGroup>
                                </Grid>
                            </Grid>
                            {/*{this.state.service === "Bois" &&*/}
                            {/*<Grid container className="Gridlabelfield">*/}
                            {/*    <Grid item className="Label">*/}
                            {/*        Type de camion :*/}
                            {/*    </Grid>*/}
                            {/*    <Grid item>*/}
                            {/*        <FormGroup>*/}
                            {/*            <FormControlLabel*/}
                            {/*                control={<Checkbox name="t1" value={this.state.typeTruck[0]}*/}
                            {/*                                   onChange={this.typeTruckChecked}/>}*/}
                            {/*                label="Renault Master"*/}
                            {/*            />*/}
                            {/*            <FormControlLabel*/}
                            {/*                control={<Checkbox name="t2" value={this.state.typeTruck[1]}*/}
                            {/*                                   onChange={this.typeTruckChecked}/>}*/}
                            {/*                label="Camion benne"*/}
                            {/*            />*/}
                            {/*            <FormControlLabel*/}
                            {/*                control={<Checkbox name="t3" value={this.state.typeTruck[2]}*/}
                            {/*                                   onChange={this.typeTruckChecked}/>}*/}
                            {/*                label="Camion 20m3"*/}
                            {/*            />*/}
                            {/*        </FormGroup>*/}
                            {/*    </Grid>*/}
                            {/*</Grid>}*/}
                            {this.state.service === "Bois" &&
                            <Grid container className="Gridlabelfield">
                                <Grid item className="Label">
                                    Quantité du bois (stère):
                                </Grid>
                                <Grid item>
                                    <TextField
                                        type="number"
                                        inputProps={{step: "0.5"}}
                                        className="number"
                                        variant="outlined"
                                        size="small"
                                        name="quantityWood"
                                        value={this.state.quantityWood}
                                        onChange={this.handleChange}
                                        required
                                    />
                                </Grid>
                            </Grid>}
                            {/* <Grid container className="Gridlabelfield">
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
                        </Grid> */}
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
                                    <input
                                        style={{backgroundColor:"floralwhite"}}
                                        id="date"
                                        type="date"
                                        name="executionDate"
                                        value={this.state.executionDate}
                                        onChange={this.handleChange}
                                    />
                                </Grid>
                            </Grid>
                            {/*<Grid container className="Gridlabelfield">*/}
                            {/*    <Grid item className="Label">*/}
                            {/*        Horaire d’exécution :*/}
                            {/*    </Grid>*/}
                            {/*    <Grid item>*/}
                            {/*        <TextField*/}
                            {/*            type="time"*/}
                            {/*            name="executionTime"*/}
                            {/*            value={this.state.executionTime}*/}
                            {/*            onChange={this.handleChange}*/}
                            {/*        />*/}
                            {/*    </Grid>*/}
                            {/*</Grid>*/}
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
                                                <Grid item key={index}>
                                                    <Grid container direction="column">
                                                        <Grid item>
                                                            <img src={URL.createObjectURL(image)} alt="" width="150"
                                                                 height="100"/>
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
                        <Button className={"MyButton"} type="button" color="primary">
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
