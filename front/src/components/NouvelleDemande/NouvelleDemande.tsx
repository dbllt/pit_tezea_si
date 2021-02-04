import React from 'react';
import {Link} from "react-router-dom";

const NouvelleDemande: React.FC = () => (
  <div>
    NouvelleDemande Component
      <Link to="/">
          <button type="button">
              Retour
          </button>
      </Link>
  </div>
);

export default NouvelleDemande;
