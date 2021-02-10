import React from 'react';
import {Link} from "react-router-dom";
import {Button} from "@material-ui/core";

const ServiceList: React.FC = () => (
    <div style={{margin: 50}}>
        <Link to="/newRequest" style={{margin: 20}}>
            <Button variant="contained">
                Service Menuiserie
            </Button>
        </Link>
        <Link to="/newRequest" style={{margin: 20}}>
            <Button variant="contained">
                Service Nettoyage
            </Button>
        </Link>
    </div>
);

export default ServiceList;
