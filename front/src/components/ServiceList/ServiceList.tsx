import React, {Component} from 'react';
import {Link} from "react-router-dom";
import {Button} from "@material-ui/core";


class ServiceList extends Component {
    render() {
        return (
            <div style={{margin: 50}}>
                <Link to={{
                    pathname: '/newRequest',
                    state: {
                        service: "Menuiserie",
                    }
                }} style={{margin: 20}}>
                    <Button variant="contained">
                        Service Menuiserie
                    </Button>
                </Link>
                <Link to={{
                    pathname: '/newRequest',
                    state: {
                        service: "Nettoyage",
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
