import React, {Component} from 'react';
import {Link} from "react-router-dom";
import {Button} from '@material-ui/core'
import API from "../../network/API";
import BusinessTable from "../BusinessTable/BusinessTable";

interface Request {
    id: string;
    task: string;
    address: string;
}

interface IProps {
}

interface IState{
    requests:Request[];
}


class RequestsList extends Component<IProps, IState> {
    state = {
        requests: []
    }


    componentDidMount() {
        API.getRequests().then((data=>{this.setState({requests:data})}));
    }

    render() {
    return (
        <div>
            <h1>Liste de Demandes</h1>

            {/*{this.state.requests.map((request:Request) => (*/}
            {/*<p>{request.task}</p>*/}
            {/*    ))*/}
            {/*}*/}

            <BusinessTable/>
            <Link to="/menu">
                <Button color="primary">
                    Retour au menu
                </Button>
            </Link>

        </div>);
}}
export default RequestsList

