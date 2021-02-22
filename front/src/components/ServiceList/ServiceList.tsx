import React, {Component} from 'react';
import {Link, Redirect} from "react-router-dom";
import {Button} from "@material-ui/core";


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
                <Link to={{
                    pathname: '/request',
                    state: {
                        service: "Bois",
                        requestId: -1,
                    }
                }} style={{margin: 20}}>
                    <Button variant="contained">
                        Service Bois
                    </Button>
                </Link>
                <Link to={{
                    pathname: '/request',
                    state: {
                        service: "Couture",
                        requestId: -1,
                    }
                }} style={{margin: 20}}>
                    <Button variant="contained">
                        Service Couture
                    </Button>
                </Link>
                <Link to={{
                    pathname: '/request',
                    state: {
                        service: "Tri", requestId: -1,
                    }
                }} style={{margin: 20}}>
                    <Button variant="contained">
                        Service Tri
                    </Button>
                </Link>
                <Link to={{
                    pathname: '/request',
                    state: {
                        service: "Recyclerie", requestId: -1,
                    }
                }} style={{margin: 20}}>
                    <Button variant="contained">
                        Service Recyclerie
                    </Button>
                </Link>
                <Link to={{
                    pathname: '/request',
                    state: {
                        service: "Enlèvements", requestId: -1,
                    }
                }} style={{margin: 20}}>
                    <Button variant="contained">
                        Service Enlèvements
                    </Button>
                </Link>
                <Link to={{
                    pathname: '/request',
                    state: {
                        service: "Estimateur", requestId: -1,
                    }
                }} style={{margin: 20}}>
                    <Button variant="contained">
                        Estimateur
                    </Button>
                </Link>
            </div>
        );
    }
}

export default ServiceList;
