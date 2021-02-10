import React from 'react';
import {Link} from "react-router-dom";
import {Button} from "@material-ui/core";

const NewRequest: React.FC = () => (
  <div>
    Nouvelle Demande
      <Link to="/">
          <Button type="button">
              Retour
          </Button>
      </Link>
  </div>
);

export default NewRequest;
