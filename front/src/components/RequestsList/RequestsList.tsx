import React, {Component} from 'react';
import {Link} from "react-router-dom";
import {Button} from '@material-ui/core'
import API from "../../network/API";


interface IProps {
}

interface IState{
    test:string;
}


class RequestsList extends Component<IProps, IState> {
    state = {
        test: ""
    }


    componentDidMount() {
        API.test().then((data=>{this.setState({test:data})}));
    }

    render() {
    return (
        <div>
            Liste de Demandes {this.state.test}

            <Link to="/menu">
                <Button color="primary">
                    Retour
                </Button>
            </Link>
        </div>);
}}
export default RequestsList

