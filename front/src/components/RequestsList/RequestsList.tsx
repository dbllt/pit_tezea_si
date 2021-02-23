import React, {Component} from 'react';
import {Link, Redirect} from "react-router-dom";
import {Button} from '@material-ui/core'
import BusinessTable from "../BusinessTable/BusinessTable";


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

class RequestsList extends Component {
    render() {
        return (
            <div>
                <RedirectionIfNotConnected/>
                <h1>Liste de Demandes</h1>

                <BusinessTable/>
                <Link to="/">
                    <Button color="primary">
                        Retour au menu
                    </Button>
                </Link>

            </div>);
    }
}

export default RequestsList

