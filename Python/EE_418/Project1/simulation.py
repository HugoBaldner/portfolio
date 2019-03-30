from ids import *
import numpy as np
import matplotlib.pyplot as plt

__author__ = "ee418"


def import_data(file=None):
    with open(file) as f:
        lines = f.readlines()
    data = [float(x.strip()) for x in lines]
    return data


# TODO: Plot accumulated offset curves for both state-of-the-art and NTP-based IDSs.
def plot_acc_offsets(ids, mode):
    if mode == 'state-of-the-art':
        # ====================== Start of Your Code =========================
        # Example: Plot accumulated offset curve for 0x184.
        #   plt.plot(ids['184-sota'].elapsed_time_sec_hist, ids['184-sota'].acc_offset_us_hist, label='0x184')
        
        # Your code goes here.
        plt.plot(ids['184-sota'].elapsed_time_sec_hist, ids['184-sota'].acc_offset_us_hist, label='0x184')
        plt.plot(ids['3d1-sota'].elapsed_time_sec_hist, ids['3d1-sota'].acc_offset_us_hist, label='0x3d1')
        plt.plot(ids['180-sota'].elapsed_time_sec_hist, ids['180-sota'].acc_offset_us_hist, label='0x180')
        plt.legend()
        plt.title("Accumulated Offset Curve for State-of-The-Art IDS, Batch Size %i" %N)
        plt.xlabel("Elapsed Time (sec)")
        plt.ylabel("Accumulated Offset (us)")
        #plt.savefig('C:\\Users\\sirwi\\Documents\\UW\\EE 418\\Project 1\\Task3sota.png', dpi = 300)
        plt.show()

        # ====================== End of Your Code =========================
    elif mode == 'ntp-based':
        # ====================== Start of Your Code =========================

        # Your code goes here
        plt.plot(ids['184-ntp'].elapsed_time_sec_hist, ids['184-ntp'].acc_offset_us_hist, label='0x184')
        plt.plot(ids['3d1-ntp'].elapsed_time_sec_hist, ids['3d1-ntp'].acc_offset_us_hist, label='0x3d1')
        plt.plot(ids['180-ntp'].elapsed_time_sec_hist, ids['180-ntp'].acc_offset_us_hist, label='0x180')
        plt.legend()
        plt.title("Accumulated Offset Curve for NTP IDS, Batch Size %i" % N)
        plt.xlabel("Elapsed Time (sec)")
        plt.ylabel("Accumulated Offset (us)")
        #plt.savefig('C:\\Users\\sirwi\\Documents\\UW\\EE 418\\Project 1\\Task3NTP.png', dpi = 300)
        plt.show()

        # ====================== End of Your Code =========================


# TODO: Implement this simulation (N=20)
def simulation_masquerade_attack(mode):
    if mode == 'state-of-the-art':
        # The following code is provided as an example.
        data_184 = import_data('C:\\Users\\sirwi\\Documents\\UW\\EE 418\\Project 1\\src/data/184.txt')
        data_3d1 = import_data('C:\\Users\\sirwi\\Documents\\UW\\EE 418\\Project 1\\src/data/3d1.txt')

        N = 20

        # Construct a new data-set with the first 1000 batches of data_184,
        # followed by 1000 batches of data_3d1. That is, the masquerade
        # attack occurs at the 1001-st batch.
        data_184 = np.asarray(data_184[0:1000 * N]) - data_184[0]  # Relative timestamps
        data_3d1 = np.asarray(data_3d1[0:1000 * N]) - data_3d1[0]  # Relative timestamps
        data = np.append(data_184, data_184[-1] + 0.1 + data_3d1)  # The 1st spoofed message occurs exactly 0.1 sec
                                                                   # (the period) after the last legitimate message.

        ids = IDS(T_sec=0.1, N=N, mode='state-of-the-art')

        batch_num = 2000
        for i in range(batch_num):
            batch = np.asarray(data[i * N:(i + 1) * N])
            ids.update(batch)

        plt.plot(ids.L_upper_hist, label='Upper Control Limit')
        plt.plot(ids.L_lower_hist, label='Lower Control Limit')
        plt.xlabel('Number of Batches')
        plt.ylabel('Control Limits')
        plt.title('Control Limits for State-of-the-Art IDS Masquerade Attack')
        plt.legend()
        #plt.savefig('C:\\Users\\sirwi\\Documents\\UW\\EE 418\\Project 1\\Task5MasqueradeSOTA.png', dpi = 300)
        plt.show()
        
    elif mode == 'ntp-based':
        # ====================== Start of Your Code =========================
        
        # Your code goes here.
        #Import data files 180 and 3d1
        data_184 = import_data('C:\\Users\\sirwi\\Documents\\UW\\EE 418\\Project 1\\src/data/184.txt')
        data_3d1 = import_data('C:\\Users\\sirwi\\Documents\\UW\\EE 418\\Project 1\\src/data/3d1.txt')
        
        #Set N size
        N = 20

        # Construct a new data-set with the first 1000 batches of data_184,
        # followed by 1000 batches of data_3d1. That is, the masquerade
        # attack occurs at the 1001-st batch.
        data_184 = np.asarray(data_184[0:1000 * N]) - data_184[0]  # Relative timestamps
        data_3d1 = np.asarray(data_3d1[0:1000 * N]) - data_3d1[0]  # Relative timestamps
        data = np.append(data_184, data_184[-1] + 0.1 + data_3d1)  # The 1st spoofed message occurs exactly 0.1 sec
                                                                   # (the period) after the last legitimate message.

        ids = IDS(T_sec=0.1, N=N, mode='ntp-based')

        batch_num = 2000
        for i in range(batch_num):
            batch = np.asarray(data[i * N:(i + 1) * N])
            ids.update(batch)
        
        #Plot results for NTP Masquerade Attack
        plt.plot(ids.L_upper_hist, label='Upper Control Limit')
        plt.plot(ids.L_lower_hist, label='Lower Control Limit')
        plt.xlabel('Number of Batches')
        plt.ylabel('Control Limits')
        plt.title('Control Limits for NTP IDS Masquerade Attack')
        plt.legend()
        #plt.savefig('C:\\Users\\sirwi\\Documents\\UW\\EE 418\\Project 1\\Task5MasqueradeNTS.png', dpi = 300)
        plt.show()
        
        # ====================== End of Your Code  =========================


# TODO: Implement this simulation (N=20)
def simulation_cloaking_attack(mode):
    if mode == 'state-of-the-art':
        # ====================== Start of Your Code =========================
        
        # Your code goes here.
        #Import data
        data_184 = import_data('C:\\Users\\sirwi\\Documents\\UW\\EE 418\\Project 1\\src/data/184.txt')
        data_180 = import_data('C:\\Users\\sirwi\\Documents\\UW\\EE 418\\Project 1\\src/data/180.txt')

        #Set N size
        N = 20

        # Construct a new data-set with the first 1000 batches of data_184,
        # followed by 1000 batches of data_180. That is, the cloaking
        # attack occurs at the 1001-st batch.
        data_184 = np.asarray(data_184[0:1000 * N]) - data_184[0]  # Relative timestamps
        data_180 = np.asarray(data_180[0:1000 * N]) - data_180[0]  # Relative timestamps
        data = np.append(data_184, data_184[-1] + 0.100029 + data_180)  # The 1st spoofed message occurs exactly 0.1 sec + 29 us
                                                                   # (the period) after the last legitimate message.

        ids = IDS(T_sec=0.1, N=N, mode='state-of-the-art')

        batch_num = 2000
        for i in range(batch_num):
            batch = np.asarray(data[i * N:(i + 1) * N])
            ids.update(batch)

        #Plot results of SOTA Cloaking Attack
        plt.plot(ids.L_upper_hist, label='Upper Control Limit')
        plt.plot(ids.L_lower_hist, label='Lower Control Limit')
        plt.xlabel('Number of Batches')
        plt.ylabel('Control Limits')
        plt.title('Control Limits for State-of-The-Art IDS Cloaking')
        plt.legend()
        #plt.savefig('C:\\Users\\sirwi\\Documents\\UW\\EE 418\\Project 1\\Task6CloakingSOTA.png', dpi = 300)
        plt.show()
        # ====================== End of Your Code =========================

    elif mode == 'ntp-based':
        # ====================== Start of Your Code =========================
        
        # Your code goes here.
        #Import data
        data_184 = import_data('C:\\Users\\sirwi\\Documents\\UW\\EE 418\\Project 1\\src/data/184.txt')
        data_180 = import_data('C:\\Users\\sirwi\\Documents\\UW\\EE 418\\Project 1\\src/data/180.txt')

        #Set N size
        N = 20

        # Construct a new data-set with the first 1000 batches of data_184,
        # followed by 1000 batches of data_180. That is, the cloaking
        # attack occurs at the 1001-st batch.
        data_184 = np.asarray(data_184[0:1000 * N]) - data_184[0]  # Relative timestamps
        data_180 = np.asarray(data_180[0:1000 * N]) - data_180[0]  # Relative timestamps
        data = np.append(data_184, data_184[-1] + 0.100029 + data_180)  # The 1st spoofed message occurs exactly 0.1 sec + 29 us
                                                                   # (the period) after the last legitimate message.

        ids = IDS(T_sec=0.1, N=N, mode='ntp-based')

        batch_num = 2000
        for i in range(batch_num):
            batch = np.asarray(data[i * N:(i + 1) * N])
            ids.update(batch)

        #Plot results of NTP Cloaking Attack
        plt.plot(ids.L_upper_hist, label='Upper Control Limit')
        plt.plot(ids.L_lower_hist, label='Lower Control Limit')
        plt.xlabel('Number of Batches')
        plt.ylabel('Control Limits')
        plt.title('Control Limits for NTP IDS Cloaking')
        plt.legend()
        #plt.savefig('C:\\Users\\sirwi\\Documents\\UW\\EE 418\\Project 1\\Task6CloakingNTP.png', dpi = 300)
        plt.show()
        # ====================== End of Your Code =========================


if __name__ == '__main__':
    # If IDS is correctly implemented, you should be able to run the following code.
    data_184 = import_data('C:\\Users\\sirwi\\Documents\\UW\\EE 418\\Project 1\\src/data/184.txt')
    data_3d1 = import_data('C:\\Users\\sirwi\\Documents\\UW\\EE 418\\Project 1\\src/data/3d1.txt')
    data_180 = import_data('C:\\Users\\sirwi\\Documents\\UW\\EE 418\\Project 1\\src/data/180.txt')

    data_184 = np.asarray(data_184) - data_184[0]
    data_3d1 = np.asarray(data_3d1) - data_3d1[0]
    data_180 = np.asarray(data_180) - data_180[0]

    ids = dict()

    N = 30      # Change this to 30 for Task 4
    ids['184-sota'] = IDS(T_sec=0.1, N=N, mode='state-of-the-art')
    ids['184-ntp'] = IDS(T_sec=0.1, N=N, mode='ntp-based')

    ids['3d1-sota'] = IDS(T_sec=0.1, N=N, mode='state-of-the-art')
    ids['3d1-ntp'] = IDS(T_sec=0.1, N=N, mode='ntp-based')

    ids['180-sota'] = IDS(T_sec=0.1, N=N, mode='state-of-the-art')
    ids['180-ntp'] = IDS(T_sec=0.1, N=N, mode='ntp-based')

    if N == 20:
        batch_num = 6000
    elif N == 30:
        batch_num = 4000
    else:
        batch_num = 6000

    for i in range(batch_num):
        batch_184 = data_184[i*N:(i+1)*N]
        ids['184-sota'].update(batch_184)
        ids['184-ntp'].update(batch_184)

        batch_3d1 = data_3d1[i*N:(i+1)*N]
        ids['3d1-sota'].update(batch_3d1)
        ids['3d1-ntp'].update(batch_3d1)

        batch_180 = data_180[i * N:(i + 1) * N]
        ids['180-sota'].update(batch_180)
        ids['180-ntp'].update(batch_180)


    # Task 2: Plot accumulated offset curves for 0x184, 0x3d1, and 0x180, for the state-of-the-art IDS.
    #plot_acc_offsets(ids, "state-of-the-art")

    # Task 3: Plot accumulated offset curves for 0x184, 0x3d1, and 0x180, for the NTP-based IDS.
    #plot_acc_offsets(ids, "ntp-based")

    # Task 4: Change N to 30, and repeat Tasks 2 and 3.
    # plot_acc_offsets(ids, "state-of-the-art")
    # plot_acc_offsets(ids, "ntp-based")

    # Task 5: Simulate the masquerade attack, and plot upper/lower control limits.
    simulation_masquerade_attack("state-of-the-art")
    simulation_masquerade_attack("ntp-based")

    # Task 6: Simulate the cloaking attack, and plot upper/lower control limits.
    simulation_cloaking_attack("state-of-the-art")
    simulation_cloaking_attack("ntp-based")