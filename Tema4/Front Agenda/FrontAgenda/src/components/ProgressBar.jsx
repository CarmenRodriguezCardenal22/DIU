import './../styles/ProgressBar.css';

function ProgressBar({ progress = 0 }) {
  // Calcular el progreso basado en un máximo de 50 productos
  const progressPercentage = Math.min(progress, 50) * 2; // Máximo 100%

  return (
    <div className="progress-container">
      <div className="progress-label">Progreso: {progressPercentage}%</div>
      <div className="progress-bar-wrapper">
        <div
          className="progress-bar"
          style={{ width: `${progressPercentage}%` }}
        ></div>
      </div>
    </div>
  );
}

export default ProgressBar;
