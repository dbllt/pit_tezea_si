import React from 'react';
import {Link} from "react-router-dom";

const ListeDemandes: React.FC = () => (
  <div>
    ListeDemandes Component
      <Link to="/">
          <button type="button">
              Retour
          </button>
      </Link>
  </div>
);

export default ListeDemandes;
