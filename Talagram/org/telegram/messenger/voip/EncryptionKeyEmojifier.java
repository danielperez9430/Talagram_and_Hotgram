package org.telegram.messenger.voip;

public class EncryptionKeyEmojifier {
    private static final String[] emojis;
    private static final int[] offsets;

    static {
        EncryptionKeyEmojifier.emojis = new String[]{"😉", "😍", "😛", "😭", "😱", "😡", "😎", "😴", "😵", "😈", "😬", "😇", "😏", "👮", "👷", "💂", "👶", "👨", "👩", "👴", "👵", "😻", "😽", "🙀", "👺", "🙈", "🙉", "🙊", "💀", "👽", "💩", "🔥", "💥", "💤", "👂", "👀", "👃", "👅", "👄", "👍", "👎", "👌", "👊", "✌", "✋", "👐", "👆", "👇", "👉", "👈", "🙏", "👏", "💪", "🚶", "🏃", "💃", "👫", "👪", "👬", "👭", "💅", "🎩", "👑", "👒", "👟", "👞", "👠", "👕", "👗", "👖", "👙", "👜", "👓", "🎀", "💄", "💛", "💙", "💜", "💚", "💍", "💎", "🐶", "🐺", "🐱", "🐭", "🐹", "🐰", "🐸", "🐯", "🐨", "🐻", "🐷", "🐮", "🐗", "🐴", "🐑", "🐘", "🐼", "🐧", "🐥", "🐔", "🐍", "🐢", "🐛", "🐝", "🐜", "🐞", "🐌", "🐙", "🐚", "🐟", "🐬", "🐋", "🐐", "🐊", "🐫", "🍀", "🌹", "🌻", "🍁", "🌾", "🍄", "🌵", "🌴", "🌳", "🌞", "🌚", "🌙", "🌎", "🌋", "⚡", "☔", "❄", "⛄", "🌀", "🌈", "🌊", "🎓", "🎆", "🎃", "👻", "🎅", "🎄", "🎁", "🎈", "🔮", "🎥", "📷", "💿", "💻", "☎", "📡", "📺", "📻", "🔉", "🔔", "⏳", "⏰", "⌚", "🔒", "🔑", "🔎", "💡", "🔦", "🔌", "🔋", "🚿", "🚽", "🔧", "🔨", "🚪", "🚬", "💣", "🔫", "🔪", "💊", "💉", "💰", "💵", "💳", "✉", "📫", "📦", "📅", "📁", "✂", "📌", "📎", "✒", "✏", "📐", "📚", "🔬", "🔭", "🎨", "🎬", "🎤", "🎧", "🎵", "🎹", "🎻", "🎺", "🎸", "👾", "🎮", "🃏", "🎲", "🎯", "🏈", "🏀", "⚽", "⚾", "🎾", "🎱", "🏉", "🎳", "🏁", "🏇", "🏆", "🏊", "🏄", "☕", "🍼", "🍺", "🍷", "🍴", "🍕", "🍔", "🍟", "🍗", "🍱", "🍚", "🍜", "🍡", "🍳", "🍞", "🍩", "🍦", "🎂", "🍰", "🍪", "🍫", "🍭", "🍯", "🍎", "🍏", "🍊", "🍋", "🍒", "🍇", "🍉", "🍓", "🍑", "🍌", "🍐", "🍍", "🍆", "🍅", "🌽", "🏡", "🏥", "🏦", "⛪", "🏰", "⛺", "🏭", "🗻", "🗽", "🎠", "🎡", "⛲", "🎢", "🚢", "🚤", "⚓", "🚀", "✈", "🚁", "🚂", "🚋", "🚎", "🚌", "🚙", "🚗", "🚕", "🚛", "🚨", "🚔", "🚒", "🚑", "🚲", "🚠", "🚜", "🚦", "⚠", "🚧", "⛽", "🎰", "🗿", "🎪", "🎭", "🇯🇵", "🇰🇷", "🇩🇪", "🇨🇳", "🇺🇸", "🇫🇷", "🇪🇸", "🇮🇹", "🇷🇺", "🇬🇧", "1⃣", "2⃣", "3⃣", "4⃣", "5⃣", "6⃣", "7⃣", "8⃣", "9⃣", "0⃣", "🔟", "❗", "❓", "♥", "♦", "💯", "🔗", "🔱", "🔴", "🔵", "🔶", "🔷"};
        EncryptionKeyEmojifier.offsets = new int[]{0, 4, 8, 12, 16};
    }

    public EncryptionKeyEmojifier() {
        super();
    }

    private static int bytesToInt(byte[] arg2, int arg3) {
        return arg2[arg3 + 3] & 255 | ((arg2[arg3] & 127) << 24 | (arg2[arg3 + 1] & 255) << 16 | (arg2[arg3 + 2] & 255) << 8);
    }

    private static long bytesToLong(byte[] arg7, int arg8) {
        return (((long)arg7[arg8 + 7])) & 255 | (((((long)arg7[arg8])) & 127) << 56 | ((((long)arg7[arg8 + 1])) & 255) << 48 | ((((long)arg7[arg8 + 2])) & 255) << 40 | ((((long)arg7[arg8 + 3])) & 255) << 32 | ((((long)arg7[arg8 + 4])) & 255) << 24 | ((((long)arg7[arg8 + 5])) & 255) << 16 | ((((long)arg7[arg8 + 6])) & 255) << 8);
    }

    public static String[] emojify(byte[] arg6) {
        if(arg6.length == 32) {
            int v0 = 5;
            String[] v1 = new String[v0];
            int v2;
            for(v2 = 0; v2 < v0; ++v2) {
                v1[v2] = EncryptionKeyEmojifier.emojis[EncryptionKeyEmojifier.bytesToInt(arg6, EncryptionKeyEmojifier.offsets[v2]) % EncryptionKeyEmojifier.emojis.length];
            }

            return v1;
        }

        throw new IllegalArgumentException("sha256 needs to be exactly 32 bytes");
    }

    public static String[] emojifyForCall(byte[] arg8) {
        int v0 = 4;
        String[] v1 = new String[v0];
        int v2;
        for(v2 = 0; v2 < v0; ++v2) {
            v1[v2] = EncryptionKeyEmojifier.emojis[((int)(EncryptionKeyEmojifier.bytesToLong(arg8, v2 * 8) % (((long)EncryptionKeyEmojifier.emojis.length))))];
        }

        return v1;
    }
}

