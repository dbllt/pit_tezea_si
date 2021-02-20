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
                        service: "Menuiserie",
                        requestContent: {
                            concierge: ""
                        }
                    }
                }} style={{margin: 20}}>
                    <Button variant="contained">
                        Service Menuiserie
                    </Button>
                </Link>
                <Link to={{
                    pathname: '/request',
                    state: {
                        service: "Nettoyage",
                        requestContent: {
                            concierge: ""
                        }
                    }
                }} style={{margin: 20}}>
                    <Button variant="contained">
                        Service Nettoyage
                    </Button>
                </Link>
            </div>
        );
    }
}

export default ServiceList;
