import React, { Component } from 'react';
import { Link, Redirect } from "react-router-dom";
import { Button } from "@material-ui/core";
import Grid from "@material-ui/core/Grid";
import API from "../../network/API";

function RedirectionIfNotConnected() {
    let temp = localStorage.getItem('token');
    if (temp === null) {
        temp = "";
    }
    let token: string = temp;
    if (token === "") {
        return <Redirect to="/login" />
    } else {
        return <div />
    }
}

class ServiceList extends Component {

    render() {
        const services = API.getServices();
        return (
            <div style={{ margin: 50 }}>
                <RedirectionIfNotConnected />
                <Grid container direction="column" justify="center" alignItems="center" spacing={5}>
                    {services.map(function (value, index) {
                        if (value !== "Conciergerie") {
                            return (
                                <Grid item>
                                    <Link to={{
                                        pathname: '/request',
                                        state: {
                                            service: value,
                                            requestId: -1,
                                        }
                                    }} style={{ margin: 20 }}>

                                        <Button variant="contained"
                                            style={{
                                                width: 300,
                                                height: 50,
                                                margin: 0,
                                                padding: 0,
                                                backgroundColor: "#8fbe40", color: 'white',
                                                verticalAlign: "middle"
                                            }}>
                                            {value}
                                        </Button>
                                    </Link>
                                </Grid>
                            )
                        }
                    }
                    )}

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
