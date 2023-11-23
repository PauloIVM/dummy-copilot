package adapters.interfaces;

import adapters.shortcutDataAdapter.ShortcutData;

// TODO: Esse ShortcutData deveria ser uma interface? Ele pertence a onde?
// Minha dúvida é especialmente pq eu preciso usar um new nele dentro do adapter,
// então só a interface parece não bastar. Talvez uma solução seja nos adapters
// ter só a interface mesmo, e tbm a interface de uma factory. E no DB eu implementar
// a factory. Faz sentido???
public interface IShortcutsDatabase {
    public ShortcutData[] get();
    public Boolean update(ShortcutData[] shortcuts);
}
