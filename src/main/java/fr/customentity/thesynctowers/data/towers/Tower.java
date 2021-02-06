package fr.customentity.thesynctowers.data.towers;

import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;
import org.bukkit.Location;
import org.bukkit.Material;

public class Tower {

    private Location location;
    private Material material;

    @Inject
    public Tower(@Assisted Location location, @Assisted Material material) {
        this.location = location;
        this.material = material;
    }

    public interface Factory {
        Tower create(Location location, Material material);
    }

    public Location getLocation() {
        return location;
    }
}
