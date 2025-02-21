import { createContext, useState } from 'react';
import PropTypes from 'prop-types';

export const MonedasContext = createContext();

export function MonedasProvider({ children }) {
  const [progress, setProgress] = useState(0);

  return (
    <MonedasContext.Provider value={{ progress, setProgress }}>
      {children}
    </MonedasContext.Provider>
  );
}

MonedasContext.propTypes = {
  children: PropTypes.node.isRequired,
};