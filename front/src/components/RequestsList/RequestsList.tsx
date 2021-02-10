import React from 'react';
import {Link} from "react-router-dom";
import {Button} from '@material-ui/core'

const RequestsList: React.FC = () => (
  <div>
    Liste de Demandes
      <Link to="/menu">
          <Button color="primary">
              Retour
          </Button>
      </Link>
  </div>
);

export default RequestsList;
