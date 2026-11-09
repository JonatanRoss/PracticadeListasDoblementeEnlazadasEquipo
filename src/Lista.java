import java.text.Collator;
import java.util.Locale;

public class Lista {
    private Nodo P;
    private Nodo F;
    private final Collator comparador;

    public Lista() {
        P = null;
        F = null;
        comparador = Collator.getInstance(new Locale("es", "MX"));
        comparador.setStrength(Collator.SECONDARY);
    }

    private int Comparar(String a, String b) {
        return comparador.compare(a, b);
    }

    private boolean DesdeFinal(String palabra) {
        String inicial = palabra.substring(0, 1);
        return Comparar(inicial, "N") >= 0;
    }

    private void InsertarAntes(Nodo R, Nodo Q) {
        if (R == null) {
            Q.Ant = F;

            if (F == null) {
                P = Q;
            } else {
                F.Sig = Q;
            }

            F = Q;
        } else {
            Q.Sig = R;
            Q.Ant = R.Ant;

            if (R.Ant == null) {
                P = Q;
            } else {
                R.Ant.Sig = Q;
            }

            R.Ant = Q;
        }
    }

    public void Insertar(String palabra, int prioridad) {
        Nodo Q = new Nodo(palabra, prioridad);
        Nodo R;

        if (prioridad == 1) {
            R = P;

            while (R != null && R.Prioridad == 1) {
                R = R.Sig;
            }
        } else if (DesdeFinal(palabra)) {
            Nodo T = F;

            while (T != null && T.Prioridad == 2
                    && Comparar(T.Info, palabra) > 0) {
                T = T.Ant;
            }

            if (T == null) {
                R = P;
            } else {
                R = T.Sig;
            }
        } else {
            R = P;

            while (R != null && R.Prioridad == 1) {
                R = R.Sig;
            }

            while (R != null && Comparar(R.Info, palabra) < 0) {
                R = R.Sig;
            }
        }

        InsertarAntes(R, Q);
    }

    public Nodo Buscar(String palabra, int prioridad) {
        Nodo T;

        if (prioridad == 1) {
            T = P;

            while (T != null && T.Prioridad == 1) {
                if (Comparar(T.Info, palabra) == 0) {
                    return T;
                }

                T = T.Sig;
            }

            return null;
        }

        if (DesdeFinal(palabra)) {
            T = F;

            while (T != null && T.Prioridad == 2) {
                int resultado = Comparar(T.Info, palabra);

                if (resultado == 0) {
                    return T;
                }

                if (resultado < 0) {
                    return null;
                }

                T = T.Ant;
            }
        } else {
            T = P;

            while (T != null && T.Prioridad == 1) {
                T = T.Sig;
            }

            while (T != null) {
                int resultado = Comparar(T.Info, palabra);

                if (resultado == 0) {
                    return T;
                }

                if (resultado > 0) {
                    return null;
                }

                T = T.Sig;
            }
        }

        return null;
    }

    private void EliminarNodo(Nodo Q) {
        if (Q.Ant == null) {
            P = Q.Sig;
        } else {
            Q.Ant.Sig = Q.Sig;
        }

        if (Q.Sig == null) {
            F = Q.Ant;
        } else {
            Q.Sig.Ant = Q.Ant;
        }

        Q.Ant = null;
        Q.Sig = null;
    }

    public String Eliminar(String palabra, int prioridad) {
        Nodo Q;

        if (prioridad == 1) {
            if (P == null || P.Prioridad != 1) {
                return null;
            }

            Q = P;
        } else {
            Q = Buscar(palabra, prioridad);
        }

        if (Q == null) {
            return null;
        }

        String eliminada = Q.Info;
        EliminarNodo(Q);
        return eliminada;
    }

    public boolean Modificar(String palabra, String nueva, int prioridad) {
        if (prioridad == 1) {
            Nodo Q = Buscar(palabra, prioridad);

            if (Q == null) {
                return false;
            }

            Q.Info = nueva;
        } else {
            if (Eliminar(palabra, prioridad) == null) {
                return false;
            }

            Insertar(nueva, prioridad);
        }

        return true;
    }

    public void Mostrar(boolean ascendente) {
        if (P == null) {
            System.out.println("Lista vacía.");
            return;
        }

        Nodo T;

        if (ascendente) {
            T = P;
        } else {
            T = F;
        }

        while (T != null) {
            System.out.println(T.Info + " | Prioridad: " + T.Prioridad);

            if (ascendente) {
                T = T.Sig;
            } else {
                T = T.Ant;
            }
        }
    }

    public void Liberar() {
        while (P != null) {
            EliminarNodo(P);
        }
    }
}