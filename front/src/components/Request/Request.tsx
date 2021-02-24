import React, {Component, createRef} from "react";
import {Button, Checkbox, Container, FormControlLabel, FormGroup, Select, TextField} from "@material-ui/core";
import Form from "@material-ui/core/FormControl";
import Grid from "@material-ui/core/Grid";
import MenuItem from "@material-ui/core/MenuItem";
import {Link, Redirect, RouteComponentProps} from "react-router-dom";
import "./Request.css";
import FormClient from "../FormClient/FormClient";
import API from "../../network/API";

interface IRequest {
    id: string,
    site: string,
    concierge: string,
    typeRequest: string,
    requestDesc: string,
    numberPerson: string,
    place: string,
    regularity: string,
    duration: string,
    material: boolean[],
    materialother: string,
    internalInfo: string,
    executionDate: string,
    executionTime: string,
    images: File [],
    photos:string[]
    requestStatus: string,
    requestAssignment: string,
    client:IClient
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
    material: boolean [],
    materialother: string,
    internalInfo: string,
    executionDate: string,
    executionTime: string,
    requestStatus: string,
    requestAssignment: string,
    images: File [],
    photos:string[],
    client: IClient,
    // request: IRequest

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
            service: localStorage.getItem('service') || "",
            redirect: false,
            id: "",
            concierge: "",
            site: "",
            typeRequest: "",
            requestDesc: "",
            numberPerson: "",
            place: "",
            regularity: "",
            duration: "",
            material:[false,false,false],
            materialother: "",
            internalInfo:"", 
            executionDate: this.dateNow(), 
            executionTime: "",      
            requestStatus: "",
            requestAssignment: "",
            images: [],
            photos:[],
            client : {
                clientStatus: "Particulier",
                company: "",
                siret:"",
                gender: "",
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
        this.callbackFunction = this.callbackFunction.bind(this);
        this.handleChange = this.handleChange.bind(this);
        this.addRequest = this.addRequest.bind(this);
    }

    dateNow(){
        let d = new Date();
        let m:any;
        let day:any;
        if(d.getMonth() < 9) m="0"+(d.getMonth()+1);
        else m=d.getMonth();

        if(d.getDate() < 9) day="0"+d.getDate();
        else day=d.getDate();

        return d.getFullYear()+"-"+m+"-"+day;
    }

    getTask(): string {
        if (this.task.current == null) {
            return "";
        } else {
            return this.task.current.value;
        }
    };

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
                    internalInfo: rr.internalInfo,
                    executionDate: rr.executionDate,
                    requestStatus: rr.requestStatus,
                    requestAssignment: rr.requestAssignment,
                    images: rr.images,
                    photos:rr.photos
                });

                API.photosAddressesToFiles(rr.photos).then((files:File[])=>{this.setState({images:files})});
            } else {
                this.setState({
                    id: "",
                    concierge: "",
                    site: "",
                    typeRequest: "",
                    requestDesc: "",
                    numberPerson: "",
                    place: "",
                    regularity: "",
                    duration: "",
                    material: [false,false,false],
                    materialother: "",
                    internalInfo:"",
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
    }

    addImage(e: any) {
        if (this.state.images.length < 3) {
            this.state.images.push(e.target.files[0])
            this.setState({images: this.state.images});
        }
    }; // upload images

    materialChecked(event:any){
        let data: boolean[] = this.state.material;
        let indice: number;
        if(event.target.name === "m1")
            indice=0;
        else if(event.target.name === "m2")
            indice=1;
        else
            indice=2;

        if(event.target.checked){
            data[indice] = true;
            this.setState({material : data })            
        }else {
            data[indice] = false;
            this.setState({material : data })      
        } 
    }

    callbackFunction(childData:IClient) {
        this.setState({client: childData})
    }

    handleChange(event:any){
        const {target: {name, value}} = event;
        const newState = {[name]: value} as Pick<IState, keyof IState>;
        this.setState(newState);
    }

    addRequest() {
        // var client: IClient = {
        //     clientStatus: "Particulier",
        //     company: "Google",
        //     gender: "M",
        //     lName: "Pouet",
        //     fName: "Grogu",
        //     phone: "06",
        //     email: "grogu@sw.mdr",
        //     address: "2 rue de la paix",
        //     cp: "35000",
        //     city: "Rennes"
        // }

        // const date1 = new Date(2021, 1, 28);
        // var request: IRequest = {
        //     id: "-1",
        //     date: "",
        //     hour: "",
        //     concierge: "Jonzé",
        //     site: "Bois",
        //     typeRequest: "Don",
        //     requestDesc: "faire un truc",
        //     numberPerson: "3",
        //     place: "boueux",
        //     regularity: "2",
        //     duration: "1 an",
        //     material: "2 camions",
        //     internalInfo: "coucou",
        //     executionDate: date1,
        //     requestStatus: "En cours",
        //     requestAssignment: "Pierre",
        //     images: this.state.images,
        //     client: client,
        //     photos:[]
        // }  
        var client = this.state.client;

        var request:IRequest={
            id: "1",
            site: this.state.service,
            concierge: this.state.concierge,            
            typeRequest:this.state.typeRequest,
            requestDesc : this.state.requestDesc,
            numberPerson: this.state.numberPerson,
            place: this.state.place,
            regularity: this.state.regularity,
            duration: this.state.duration,
            material: this.state.material,
            materialother: this.state.materialother,
            internalInfo:this.state.internalInfo,
            executionDate: this.state.executionDate,
            executionTime:this.state.executionTime,
            images: this.state.images,
            requestStatus: "",
            requestAssignment: "",            
            client:client,
            photos:[]
        }
        if (true) {
          //  API.addRequest(request).then(() => this.setState({redirect: true}));
        }

        console.log(client);

    }

    render() {
        return (
            <Container fixed>
                <RedirectionIfNotConnected/>
                <h1>Service {this.state.service}</h1>
                <h1>Enregistrer une demande client</h1>

                <Form className="form">  
                <Grid container direction="column" alignItems="flex-start" justify="center" >
                    <Grid item>
                     <FormClient parentCallback={this.callbackFunction}/>
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
                                    onChange={this.handleChange} />
                            </Grid>
                        </Grid>
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
                                        control={<Checkbox name="m1" value={this.state.material[0]} onChange={this.materialChecked}/>}
                                        label="Matériel lié au port de charge"
                                    />
                                    <FormControlLabel
                                        control={<Checkbox name="m2" value={this.state.material[1]} onChange={this.materialChecked}/>}
                                        label="Matériel lié à la prestation"
                                    />
                                    <FormControlLabel
                                        control={<Checkbox name="m3" value={this.state.material[2]} onChange={this.materialChecked} />}
                                        label="Autre"
                                    />
                                   { this.state.material[2] && <TextField name="materialother" value={this.state.materialother} 
                                                                    onChange={this.handleChange}/> }                                     
                                </FormGroup>
                            </Grid>
                        </Grid>
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
                                    id="date"
                                    type="date"
                                    name="executionDate"
                                    value={this.state.executionDate}                                                                      
                                    onChange={this.handleChange}
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
                                    name="executionTime"
                                    value={this.state.executionTime}
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
                                            <Grid item key={index}>
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