import React, {Component} from 'react';
import {Link, Redirect} from "react-router-dom";
import {Button} from "@material-ui/core";
import Grid from "@material-ui/core/Grid";


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

class ServiceList extends Component {
    render() {
        return (
            <div style={{margin: 50}}>
                <RedirectionIfNotConnected/>
                <Grid container direction="column" justify="center" alignItems="center" spacing={5}>
                    <Grid item>
                        <Link to={{
                            pathname: '/request',
                            state: {
                                service: "Bois",
                                requestId: -1,
                            }
                        }} style={{margin: 20}}>
                            
                            <Button variant="contained"
                                    style={{
                                        width: 300,
                                        height: 50,
                                        margin: 0 ,
                                        padding: 0,
                                        backgroundColor: "#8fbe40",color: 'white',
                                        verticalAlign: "middle"}}>
                                Service Bois
                            </Button>
                        </Link>
                    </Grid>
                    <Grid item>
                        <Link to={{
                            pathname: '/request',
                            state: {
                                service: "Couture",
                                requestId: -1,
                            }
                        }} style={{margin: 20}}>
                            <Button variant="contained"
                                    style={{
                                        width: 300,
                                        height: 50,
                                        margin: 0 ,
                                        padding: 0,
                                        backgroundColor: "#8fbe40",color: 'white',
                                        verticalAlign: "middle"}}>
                                Service Couture
                            </Button>
                        </Link>
                    </Grid>
                    <Grid item>
                        <Link to={{
                            pathname: '/request',
                            state: {
                                service: "Tri", requestId: -1,
                            }
                        }} style={{margin: 20}}>
                            
                            <Button variant="contained"
                                    style={{
                                        minWidth: 200,
                                        width: 300,
                                        height: 50,
                                        margin: 0 ,
                                        padding: 0,
                                        backgroundColor: "#8fbe40",color: 'white',
                                        verticalAlign: "middle"}}>
                                Service Tri
                            </Button>
                        </Link>
                    </Grid>
                    <Grid item>
                        <Link to={{
                            pathname: '/request',
                            state: {
                                service: "Recyclerie", requestId: -1,
                            }
                        }} style={{margin: 20}}>
                            
                            <Button variant="contained"
                                    style={{
                                        minWidth: 200,
                                        width: 300,
                                        height: 50,
                                        margin: 0 ,
                                        padding: 0,
                                        backgroundColor: "#8fbe40",color: 'white',
                                        verticalAlign: "middle"}}>
                                Service Recyclerie
                            </Button>
                        </Link>
                    </Grid>
                    <Grid item>
                        <Link to={{
                            pathname: '/request',
                            state: {
                                service: "Enlèvements", requestId: -1,
                            }
                        }} style={{margin: 20}}>
                            
                            <Button variant="contained"
                                    style={{
                                        minWidth: 200,
                                        width: 300,
                                        height: 50,
                                        margin: 0 ,
                                        padding: 0,
                                        backgroundColor: "#8fbe40",color: 'white',
                                        verticalAlign: "middle"}}>
                                Service Enlèvements
                            </Button>
                        </Link>
                    </Grid>
                    <Grid item>
                        <Link to={{
                            pathname: '/request',
                            state: {
                                service: "Estimateur", requestId: -1,
                            }
                        }} style={{margin: 20}}>
                            
                            <Button variant="contained"
                                    style={{
                                        minWidth: 200,
                                        width: 300,
                                        height: 50,
                                        margin: 0 ,
                                        padding: 0,
                                        backgroundColor: "#8fbe40",color: 'white',
                                        verticalAlign: "middle"}}>
                                Estimateur
                            </Button>
                        </Link>
                    </Grid>
                    <Grid item>

                        <Link to="/">
                            <Button className={"MyButton"} type="button" color="primary">
                                Retour
                            </Button>
                        </Link>
                    </Grid>
                </Grid>
            </div>
        );
    }
}

export default ServiceList;
