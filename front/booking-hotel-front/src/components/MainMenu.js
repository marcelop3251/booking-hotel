import { Link } from "react-router-dom";

export const MainMenu = (props) => ( 
    <nav class="navbar navbar-expand-lg navbar-light bg-light">
    <div class="container px-4 px-lg-5">
        <Link className="navbar-brand" to="/home">Hotéis</Link>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation"><span class="navbar-toggler-icon"></span></button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0 ms-lg-4">
                <li class="nav-item"><Link className="nav-link active" to="/home">Home</Link></li>

                <li class="nav-item"><a class="nav-link" href="#!">Serviços de Quarto</a></li>
                <li class="nav-item"><a class="nav-link" href="#!">Refeição</a></li>
                <li class="nav-item"><a class="nav-link" href="#!">Reservas</a></li>
                <li class="nav-item"><a class="nav-link" href="#!">Check-in</a></li>
                <li class="nav-item"><a class="nav-link" href="#!">Check-out</a></li>
            </ul>
        </div>
    </div>
</nav>
);